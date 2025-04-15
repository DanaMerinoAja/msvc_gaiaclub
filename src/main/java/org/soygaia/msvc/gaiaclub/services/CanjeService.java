package org.soygaia.msvc.gaiaclub.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.soygaia.msvc.gaiaclub.models.dtos.CanjeDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.CanjeRequestDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.CanjesRequestDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.UltiCanjeDTO;
import org.soygaia.msvc.gaiaclub.models.entity.CanjeEntity;
import org.soygaia.msvc.gaiaclub.models.entity.RecompensaEntity;
import org.soygaia.msvc.gaiaclub.repositories.CanjeRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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


    public CanjeDTO registrarCanje(CanjeRequestDTO canjeDTO) {
        // 1. Verificar recompensa y disponibilidad
        RecompensaEntity recompensa = recompensaService.obtenerRecompensaValida(canjeDTO.getRecompensaId());

        // 2. Canjear puntos (con la lógica implementada en PuntosService)
        puntosService.canjearPuntos(canjeDTO.getClienteId(), recompensa.getPuntosRequeridos()* canjeDTO.getCantidadRecompensa());
        // 3. Registrar el canje
        CanjeEntity canje = new CanjeEntity();
        canje.setIdCliente(canje.getIdCliente());
        canje.setRecompensa(recompensa);
        canje.setFecha(LocalDate.now());
        canje.setPuntosCanjeados(recompensa.getPuntosRequeridos());
        canjeRepository.persist(canje);

        // 4. Restar stock
        recompensa.setStock(recompensa.getStock() - canjeDTO.getCantidadRecompensa());
        return new CanjeDTO(
                recompensa.getNombre(),
                canje.getPuntosCanjeados(),
                canje.getFecha(),
                recompensa.getStock(),
                "¡Canje exitoso!"
        );
    }

    public List<CanjeDTO> registrarCanjes(@Valid CanjesRequestDTO dtos) {
        List<CanjeDTO> canjesRegitrados = new ArrayList<>();
        if(dtos.getTotalPuntos()<= puntosService.getTotalPuntosDisponiblesPorCliente(dtos.getClienteId())){
            for (CanjeRequestDTO dto : dtos.getCanjeRequestDTOS()){
                canjesRegitrados.add(registrarCanje(dto));
            }
        }
        return canjesRegitrados;
    }

    public List<UltiCanjeDTO> ultimosCanjesCliente(Long idCliente) {
        return new ArrayList<>();
    }
}
