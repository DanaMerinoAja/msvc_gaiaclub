package org.soygaia.msvc.gaiaclub.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.hibernate.service.spi.ServiceException;
import org.soygaia.msvc.gaiaclub.config.properties.ErrorCode;
import org.soygaia.msvc.gaiaclub.models.dtos.*;
import org.soygaia.msvc.gaiaclub.models.entity.CanjeEntity;
import org.soygaia.msvc.gaiaclub.models.entity.DetalleCanjeEntity;
import org.soygaia.msvc.gaiaclub.repositories.*;

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
    RecompensaRepository recompensaRepository;

    @Inject
    PeriodoRepository periodoRepository;

    @Inject
    DetalleCanjeRepository detalleCanjeRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    MiembroRepository miembroRepository;

    public CanjeEntity registrarCanje(@Valid CanjeRequestDTO canje) {

        List<DetalleCanje> detalleCanjes = canje.getDetallesCanje();

        if(detalleCanjes == null) {
            throw new ServiceException(ErrorCode.CANJE_DETAIL_NULL.getCode() + ": " + ErrorCode.CANJE_DETAIL_NULL.getDescription());
        }
        if(detalleCanjes.isEmpty()) {
            throw new ServiceException(ErrorCode.CANJE_DETAIL_NULL.getCode() + ": " + ErrorCode.CANJE_DETAIL_NULL.getDescription());
        }
        if(puntosService.getTotalPuntosDisponiblesPorCliente(canje.getMiembroId()) < canje.getTotalPuntos()){
            throw new ServiceException(ErrorCode.PUNTOS_INSUFICIENTES.getCode() + ": " + ErrorCode.PUNTOS_INSUFICIENTES.getDescription());
        }

        try{
            CanjeEntity canjeEntity = new CanjeEntity();

            canjeEntity.setMiembro(miembroRepository.findById(canje.getMiembroId()));
            canjeEntity.setFecha(LocalDate.now());
            canjeEntity.setPeriodo(periodoRepository.findById(canje.getPeriodo()));

            canjeRepository.persist(canjeEntity);

            puntosService.canjearPuntos(canje.getMiembroId(), canje.getTotalPuntos());

            for(DetalleCanje detalleCanje : canje.getDetallesCanje()){
                DetalleCanjeEntity detalleCanjeEntity = new DetalleCanjeEntity();

                detalleCanjeEntity.setDcjCanjePadre(canjeEntity);
                detalleCanjeEntity.setCantidadRecompensa(detalleCanje.getCantidadRecompensa());
                detalleCanjeEntity.setPuntosDetCanje(detalleCanje.getPuntosDetCanje());
                detalleCanjeEntity.setDcjRecompensa(recompensaRepository.findById(detalleCanje.getRecompensaId()));

                detalleCanjeRepository.persist(detalleCanjeEntity);
            }

            entityManager.flush();
            entityManager.refresh(canjeEntity);

            return canjeEntity;

        } catch(Exception e) {
            e.printStackTrace();
            throw new ServiceException(ErrorCode.REGISTER_CANJE_FAILED.getCode()+ ": " + ErrorCode.REGISTER_CANJE_FAILED.getDescription(),e);
        }
    }

    public List<UltimosCanjesDTO> ultimosCanjesCliente(Long miembroId, Long periodoId) {
        List<DetalleCanjeEntity> detalleCanjes = detalleCanjeRepository.canjesPorPeriodo(periodoId, miembroId);
        return new ArrayList<>();
    }
}
