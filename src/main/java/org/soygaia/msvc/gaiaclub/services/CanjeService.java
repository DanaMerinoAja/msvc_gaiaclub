package org.soygaia.msvc.gaiaclub.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.hibernate.service.spi.ServiceException;
import org.soygaia.msvc.gaiaclub.config.properties.ErrorCode;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.canjes.CanjeRequestDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.canjes.DetalleCanjePost;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.canjes.UltimosCanjesDTO;
import org.soygaia.msvc.gaiaclub.models.entity.*;
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

    public CanjeEntity registrarCanjeRecompensa(@Valid CanjeRequestDTO canje) {

        List<DetalleCanjePost> detalleCanjePosts = canje.getDetallesCanje();

        if(detalleCanjePosts == null || detalleCanjePosts.isEmpty()) {
            throw new ServiceException(ErrorCode.CANJE_DETAIL_NULL.getCode() + ": " + ErrorCode.CANJE_DETAIL_NULL.getDescription());
        }
        if(puntosService.getTotalPuntosDisponiblesPorCliente(canje.getMiembroId()) < canje.getTotalPuntos()){
            throw new ServiceException(ErrorCode.PUNTOS_INSUFICIENTES.getCode() + ": " + ErrorCode.PUNTOS_INSUFICIENTES.getDescription());
        }

        try{
            CanjeEntity canjeEntity = new CanjeEntity();

            canjeEntity.setMiembro(miembroRepository.findById(canje.getMiembroId()));
            canjeEntity.setFecha(LocalDate.now());
            canjeEntity.setPeriodo(periodoRepository.findById(canje.getPeriodoId()));
            canjeEntity.setEstado(CanjeEntity.EstadoCanje.POR_ENTREGAR);
            canjeEntity.setTipoCanje(CanjeEntity.TipoCanje.RECOMPENSA);

            canjeRepository.persist(canjeEntity);

            int puntosAux = 0;

            for(DetalleCanjePost detalleCanjePost : canje.getDetallesCanje()){
                DetalleCanjeEntity detalleCanjeEntity = new DetalleCanjeEntity();

                RecompensaEntity r = recompensaRepository.findById(detalleCanjePost.getRecompensaId());

                detalleCanjeEntity.setDcjCanjePadre(canjeEntity);
                detalleCanjeEntity.setCantidadRecompensa(detalleCanjePost.getCantidadRecompensa());
                detalleCanjeEntity.setPuntosDetCanje(r.getPuntosRequeridos()* detalleCanjePost.getCantidadRecompensa());
                detalleCanjeEntity.setDcjRecompensa(r);

                r.setStock(r.getStock()- detalleCanjePost.getCantidadRecompensa());
                puntosAux += r.getPuntosRequeridos()* detalleCanjePost.getCantidadRecompensa();

                detalleCanjeRepository.persist(detalleCanjeEntity);
            }

            //verificación en caso haya habido un problema de sincronización frontend/backend -> se prioriza el backend
            if(puntosAux == canje.getTotalPuntos()){
                puntosService.canjearPuntos(canje.getMiembroId(), canje.getTotalPuntos());
            }
            else {
                canjeRepository.delete(canjeEntity);
                throw new ServiceException(ErrorCode.REGISTER_CANJE_FAILED.getDescription());
            }
            entityManager.flush();
            entityManager.refresh(canjeEntity);
            return canjeEntity;
        } catch(Exception e) {
            throw new ServiceException(ErrorCode.REGISTER_CANJE_FAILED.getCode()+ ": " + ErrorCode.REGISTER_CANJE_FAILED.getDescription(),e);
        }
    }

    public CanjeEntity registrarCanjeVale(ValeEntity vale, MiembroClubEntity miembroClub){
        if(puntosService.getTotalPuntosDisponiblesPorCliente(miembroClub.getIdMiembro()) < vale.getPuntosRequeridos()){
            throw new ServiceException(ErrorCode.PUNTOS_INSUFICIENTES.getCode() + ": " + ErrorCode.PUNTOS_INSUFICIENTES.getDescription());
        }
        CanjeEntity canjeEntity = new CanjeEntity();

        canjeEntity.setMiembro(miembroClub);
        canjeEntity.setFecha(LocalDate.now());
        canjeEntity.setPeriodo(vale.getPeriodo());
        canjeEntity.setEstado(CanjeEntity.EstadoCanje.ENTREGADO_VALE);
        canjeEntity.setTipoCanje(CanjeEntity.TipoCanje.VALE);

        canjeRepository.persist(canjeEntity);

        return canjeEntity;
    }

    public List<UltimosCanjesDTO> ultimosCanjesCliente(Long miembroId, Long periodoId) {
        List<DetalleCanjeEntity> detalleCanjes = detalleCanjeRepository.canjesPorPeriodo(periodoId, miembroId);
        return new ArrayList<>();
    }

    public void eliminarCanje(Long canjeId){
        CanjeEntity canjeEntity = canjeRepository.findById(canjeId);

        MiembroClubEntity miembroEntity = canjeEntity.getMiembro();


    }

}
