package org.soygaia.msvc.gaiaclub.services;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.hibernate.service.spi.ServiceException;
import org.soygaia.msvc.gaiaclub.config.properties.Constantes;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.panelcliente.PuntoMovimientoDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.panleadministracion.GeneralInfoAdminDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.ecommerce.OrdenDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.puntos.PuntosDisponiblesDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.puntos.PuntosRegistroDTO;
import org.soygaia.msvc.gaiaclub.models.entity.BonificacionEntity;
import org.soygaia.msvc.gaiaclub.models.entity.GeneralInfoEntity;
import org.soygaia.msvc.gaiaclub.models.entity.MiembroClubEntity;
import org.soygaia.msvc.gaiaclub.models.entity.PuntosEntity;
import org.soygaia.msvc.gaiaclub.repositories.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class PuntosService {
    @Inject
    PuntosRepository puntosRepository;

    @Inject
    OrdenRepository ordenRepository;

    @Inject
    MiembroRepository miembroRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    GeneralInfoRepository generalInfoRepository;

    @Inject
    BonificacionRepository bonificacionRepository;

    public PuntosEntity registrarPuntosIn(PuntosRegistroDTO puntos){

        PuntosEntity puntosEntity = new PuntosEntity();
        puntosEntity.setMiembro(miembroRepository.findById(puntos.getIdCliente()));
        puntosEntity.setFechaEmision(LocalDate.now());
        puntosEntity.setFechaCaducidad(puntosEntity.getFechaEmision().plusMonths(Constantes.mesesVigencia));
        puntosEntity.setEstado(PuntosEntity.EstadoPuntos.VIGENTE);

        try {
            if(puntos.getTipoOrigen().equals(PuntosEntity.TipoOrigen.COMPRA.toString())){
                OrdenDTO ordenDTO = ordenRepository.findOrdenId(puntos.getIdOrigen());
                puntosEntity.setTotalPuntos((int) (ordenDTO.getTotal()/Constantes.valorCompra*Constantes.puntosPorCompra));
                puntosEntity.setTipoOrigen(PuntosEntity.TipoOrigen.COMPRA);
            }
        } catch (Exception ex){
            throw new ServiceException("Error en el registro: " + ex.getMessage());
        }

        puntosEntity.setPuntosCanjeados(0);
        puntosEntity.setIdOrigen(puntos.getIdOrigen());

        puntosRepository.persist(puntosEntity);


        return puntosEntity;
    }

    public String registrarPuntosBonificacion(String miembroDNI, Long bonificacionID){
        PuntosEntity puntosEntity = new PuntosEntity();
        try {
            MiembroClubEntity miembro = miembroRepository.findByDNI(miembroDNI);
            BonificacionEntity bonificacionEntity = bonificacionRepository.findById(bonificacionID);

            puntosEntity.setMiembro(miembro);
            puntosEntity.setFechaEmision(LocalDate.now());
            puntosEntity.setFechaCaducidad(puntosEntity.getFechaEmision().plusMonths(Constantes.mesesVigencia));
            puntosEntity.setTipoOrigen(PuntosEntity.TipoOrigen.BONIFICACION);
            puntosEntity.setTotalPuntos(bonificacionEntity.getPuntos());
            puntosEntity.setEstado(PuntosEntity.EstadoPuntos.VIGENTE);
            puntosEntity.setIdOrigen(bonificacionID);
            puntosRepository.persist(puntosEntity);
            entityManager.refresh(puntosEntity);
            return "Se asignaron " + puntosEntity.getTotalPuntos() + " por " + bonificacionEntity.getNombre() + " a " + miembro.getNombresCompletos();
        } catch (Exception ex) {
            return "No se pudo registrar la operación :( \n" + ex.getMessage();
        }

    }

    public void registrarPuntosNuevoMiembro(MiembroClubEntity miembro){
        PuntosEntity puntosEntity = new PuntosEntity();
        puntosEntity.setMiembro(miembro);
        puntosEntity.setFechaEmision(LocalDate.now());
        puntosEntity.setFechaCaducidad(puntosEntity.getFechaEmision().plusMonths(Constantes.mesesVigencia));
        puntosEntity.setTipoOrigen(PuntosEntity.TipoOrigen.BONIFICACION);
        puntosEntity.setTotalPuntos(Constantes.bonificacionBienvenida);
        puntosEntity.setEstado(PuntosEntity.EstadoPuntos.VIGENTE);
        puntosEntity.setIdOrigen((long)0);
        puntosRepository.persist(puntosEntity);
        entityManager.refresh(puntosEntity);
    }

    public Long getTotalPuntosDisponiblesPorCliente(Long miembroId) {
        return entityManager.createQuery(
                        "SELECT SUM(p.totalPuntos) FROM PuntosEntity p WHERE p.estado = 'VIGENTE' AND p.miembro.id = :miembroId",
                            Long.class
                ).setParameter("miembroId", miembroId)
                .getSingleResult();
    }

    public Long getTotalPuntosCercanosVencerPorCliente(Long miembroId) {
        return entityManager.createQuery(
                        "SELECT SUM(p.totalPuntos) FROM PuntosEntity p WHERE p.estado = 'VIGENTE' AND p.miembro.id = :miembroId AND p.fechaCaducidad <= :alertaVencimiento",
                        Long.class)
                .setParameter("miembroId", miembroId)
                .setParameter("alertaVencimiento", LocalDate.now().plusDays(Constantes.alertVencimiento))
                .getSingleResult();
    }

    public List<PuntosEntity> getPuntosVigentesOrdenados(Long miembroId) {
        return puntosRepository.find(
                "miembro.id = ?1 AND estado = 'VIGENTE'",
                Sort.ascending("fechaCaducidad"),
                miembroId
        ).list();
    }

    public void canjearPuntos(Long miembroId, int puntosACanjear) {

        List<PuntosEntity> disponibles = getPuntosVigentesOrdenados(miembroId);

        int puntosRestantes = puntosACanjear;

        for (PuntosEntity punto : disponibles) {
            int disponiblesEnRegistro = punto.getTotalPuntos();

            if (puntosRestantes >= disponiblesEnRegistro) {
                punto.setPuntosCanjeados(disponiblesEnRegistro);
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
                nuevo.setMiembro(miembroRepository.findById(miembroId));
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

    //acá límite tiene una función como de paginación
    public List<PuntoMovimientoDTO> obtenerUltimosMovimientos(Long idMiembro, int limite) {
        return puntosRepository
                .find("miembro.id = ?1 ORDER BY fecha DESC", idMiembro)
                .stream()
                .limit(limite)
                .map(p ->
                        new PuntoMovimientoDTO(
                                p.getEstado().equals(PuntosEntity.EstadoPuntos.VIGENTE) ?
                                        p.getFechaCanje():
                                        p.getEstado().equals(PuntosEntity.EstadoPuntos.CANJEADO) ? p.getFechaCanje(): p.getFechaCaducidad(),
                                p.getEstado().toString(),
                                p.getEstado().equals(PuntosEntity.EstadoPuntos.CANJEADO) ? p.getPuntosCanjeados(): p.getTotalPuntos()
                        )
                )
                .collect(Collectors.toList());
    }

    public void revisarVigencia(){
        puntosRepository.caducarVigentes();
    }

}
