package org.soygaia.msvc.gaiaclub.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.hibernate.service.spi.ServiceException;
import org.soygaia.msvc.gaiaclub.config.properties.ErrorCode;
import org.soygaia.msvc.gaiaclub.models.dtos.canjes.CanjeRequestDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.canjes.DetalleCanjePost;
import org.soygaia.msvc.gaiaclub.models.dtos.canjes.UltimosCanjesDTO;
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

    public CanjeEntity registrarCanje(@Valid CanjeRequestDTO canje) {

        List<DetalleCanjePost> detalleCanjePosts = canje.getDetallesCanje();

        if(detalleCanjePosts == null) {
            throw new ServiceException(ErrorCode.CANJE_DETAIL_NULL.getCode() + ": " + ErrorCode.CANJE_DETAIL_NULL.getDescription());
        }
        if(detalleCanjePosts.isEmpty()) {
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

            canjeRepository.persist(canjeEntity);

            int puntosAux = 0;

            for(DetalleCanjePost detalleCanjePost : canje.getDetallesCanje()){
                DetalleCanjeEntity detalleCanjeEntity = new DetalleCanjeEntity();

                RecompensaEntity r = recompensaRepository.findById(detalleCanjePost.getRecompensaId());
                //En caso la recompensa sea un producto, se verifica el stock
                if(r instanceof RecompensaProductoEntity){
                    if(r.getStock()>= detalleCanjePost.getCantidadRecompensa()){
                        detalleCanjeEntity.setDcjCanjePadre(canjeEntity);
                        detalleCanjeEntity.setCantidadRecompensa(detalleCanjePost.getCantidadRecompensa());
                        detalleCanjeEntity.setPuntosDetCanje(r.getPuntosRequeridos()* detalleCanjePost.getCantidadRecompensa());
                        detalleCanjeEntity.setDcjRecompensa(r);

                        r.setStock(r.getStock()- detalleCanjePost.getCantidadRecompensa());
                        puntosAux += r.getPuntosRequeridos()* detalleCanjePost.getCantidadRecompensa();
                    } else {
                        canjeRepository.delete(canjeEntity);
                        throw new ServiceException(ErrorCode.STOCK_INSUFICIENTE.getDescription());
                    }

                } else {
                    detalleCanjeEntity.setDcjCanjePadre(canjeEntity);
                    detalleCanjeEntity.setCantidadRecompensa(detalleCanjePost.getCantidadRecompensa());
                    detalleCanjeEntity.setPuntosDetCanje(r.getPuntosRequeridos());
                    detalleCanjeEntity.setDcjRecompensa(r);

                    puntosAux += r.getPuntosRequeridos();
                }

                //por si acaso
                if(puntosAux == canje.getTotalPuntos()){
                    puntosService.canjearPuntos(canje.getMiembroId(), canje.getTotalPuntos());
                }
                else {
                    canjeRepository.delete(canjeEntity);
                    throw new ServiceException(ErrorCode.REGISTER_CANJE_FAILED.getDescription());
                }
                detalleCanjeRepository.persist(detalleCanjeEntity);
            }
            entityManager.flush();
            entityManager.refresh(canjeEntity);
            return canjeEntity;

        } catch(Exception e) {
            throw new ServiceException(ErrorCode.REGISTER_CANJE_FAILED.getCode()+ ": " + ErrorCode.REGISTER_CANJE_FAILED.getDescription(),e);
        }
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
