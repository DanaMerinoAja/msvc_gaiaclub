package org.soygaia.msvc.gaiaclub.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.soygaia.msvc.gaiaclub.models.dtos.CanjeDTO;
import org.soygaia.msvc.gaiaclub.models.entity.CanjeEntity;
import org.soygaia.msvc.gaiaclub.models.entity.RecompensaEntity;
import org.soygaia.msvc.gaiaclub.repositories.CanjeRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ApplicationScoped
@Transactional
public class CanjeService {
    @Inject
    PuntosService puntosService;

    @Inject
    CanjeRepository canjeRepository;

    @Inject
    RecompensaService recompensaService;

    @PersistenceContext
    EntityManager entityManager;


    public CanjeDTO registrarCanje(Long idCliente, Long idRecompensa) {
        // 1. Verificar recompensa y disponibilidad
        RecompensaEntity recompensa = recompensaService.obtenerRecompensaValida(idRecompensa);

        // 2. Canjear puntos (usa la lógica que ya tienes)
        puntosService.canjearPuntos(idCliente, recompensa.getPuntosRequeridos());

        // 3. Registrar el canje
        CanjeEntity canje = new CanjeEntity();
        canje.setIdCliente(idCliente);
        canje.setRecompensa(recompensa);
        canje.setFecha(LocalDate.now());
        canje.setPuntosUsados(recompensa.getPuntosRequeridos());
        canjeRepository.persist(canje);

        // 4. Restar stock
        recompensa.setStock(recompensa.getStock() - 1);
        return new CanjeDTO(
                recompensa.getNombre(),
                canje.getPuntosUsados(),
                canje.getFecha(),
                recompensa.getStock(),
                "¡Canje exitoso! 🎉"
        );
    }

}
