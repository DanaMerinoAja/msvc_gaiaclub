package org.soygaia.msvc.gaiaclub.services;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.soygaia.msvc.gaiaclub.models.dtos.PuntosDisponiblesDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.PuntosRegistroDTO;
import org.soygaia.msvc.gaiaclub.models.entity.PuntosEntity;
import org.soygaia.msvc.gaiaclub.repositories.PuntosRepository;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
@Transactional
public class PuntosService {
    @Inject
    PuntosRepository puntosRepository;

    @PersistenceContext
    EntityManager entityManager;



    @ConfigProperty(name = "gaia.puntos.meses-vigencia", defaultValue = "10")
    long mesesVigencia;

    public PuntosEntity registrarPuntosIn(PuntosRegistroDTO puntos){
        PuntosEntity puntosEntity = new PuntosEntity();
        puntosEntity.setIdCliente(puntos.getIdCliente());
        puntosEntity.setFechaEmision(puntos.getFechaEmision());
        puntosEntity.setFechaCaducidad(puntos.getFechaEmision().plusMonths(mesesVigencia));
        puntosEntity.setEstado(puntosEntity.getFechaCaducidad().isAfter(LocalDate.now()) ? PuntosEntity.EstadoPuntos.VIGENTE: PuntosEntity.EstadoPuntos.CADUCADO);
        puntosEntity.setTotalPuntos(puntos.getTotalPuntos());
        puntosEntity.setPuntosCanjeados(0);
        puntosEntity.setIdOrigen(puntos.getIdOrigen());
        puntosEntity.setTipoOrigen(PuntosEntity.TipoOrigen.COMPRA);

        entityManager.persist(puntosEntity);
        return puntosEntity;
    }

    public Long getTotalPuntosDisponiblesPorCliente(Long clienteId) {
        return entityManager.createQuery(
                        "SELECT SUM(p.totalPuntos) FROM PuntosEntity p WHERE p.estado = 'VIGENTE' AND p.idCliente = :clienteId",
                            Long.class
                ).setParameter("clienteId", clienteId)
                .getSingleResult();
    }



    public List<PuntosEntity> getPuntosVigentesOrdenados(Long clienteId) {
        return puntosRepository.find(
                "idCliente = ?1 AND estado = 'VIGENTE'",
                Sort.ascending("fechaCaducidad"),
                clienteId
        ).list();
    }

    public void canjearPuntos(Long clienteId, int puntosACanjear) {
        List<PuntosEntity> disponibles = getPuntosVigentesOrdenados(clienteId);

        int puntosRestantes = puntosACanjear;

        for (PuntosEntity punto : disponibles) {
            int disponiblesEnRegistro = punto.getTotalPuntos();

            if (puntosRestantes >= disponiblesEnRegistro) {
                punto.setPuntosCanjeados(punto.getTotalPuntos());
                punto.setEstado(PuntosEntity.EstadoPuntos.CANJEADO);
                punto.setFechaCanje(LocalDate.now());
                puntosRestantes -= disponiblesEnRegistro;
            } else {
                // Marcar el registro original como canjeado completamente
                punto.setPuntosCanjeados(puntosRestantes);
                punto.setEstado(PuntosEntity.EstadoPuntos.CANJEADO);
                punto.setFechaCanje(LocalDate.now());

                // Crear nuevo registro con sobrantes
                int sobrantes = disponiblesEnRegistro - puntosRestantes;

                PuntosEntity nuevo = new PuntosEntity();
                nuevo.setIdOrigen(punto.getIdOrigen());
                nuevo.setTipoOrigen(punto.getTipoOrigen());
                nuevo.setFechaEmision(punto.getFechaEmision());
                nuevo.setFechaCaducidad(punto.getFechaCaducidad());
                nuevo.setIdCliente(punto.getIdCliente());
                nuevo.setEstado(PuntosEntity.EstadoPuntos.VIGENTE);
                nuevo.setTotalPuntos(sobrantes);
                nuevo.setPuntosCanjeados(0);

                puntosRepository.persist(nuevo);
                break;
            }
        }
    }

    //No se usa actualmente, pero podría servir en caso cambie la lógica respecto a los puntos
    public PuntosDisponiblesDTO obtenerPuntosVigentesOrdenados(Long clienteId) {
        List<PuntosEntity> puntos = puntosRepository.find(
                "idCliente = ?1 AND estado = 'VIGENTE'", Sort.ascending("fechaCaducidad"), clienteId
        ).list();

        int total = puntos.stream()
                .mapToInt(p -> p.getTotalPuntos() - p.getPuntosCanjeados())
                .sum();

        return new PuntosDisponiblesDTO(puntos, total);
    }

}
