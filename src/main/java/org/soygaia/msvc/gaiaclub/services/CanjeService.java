package org.soygaia.msvc.gaiaclub.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.hibernate.service.spi.ServiceException;
import org.soygaia.msvc.gaiaclub.config.properties.ErrorCode;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.panelcanjes.CanjeResumenDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.panelcanjes.DetalleRecompensaCanjeDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.canjes.CanjeGetDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.canjes.CanjeRequestDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.canjes.DetalleCanjePost;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.miembro.MiembroInfoActDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.productos.RecompensaActInfoDTO;
import org.soygaia.msvc.gaiaclub.models.entity.*;
import org.soygaia.msvc.gaiaclub.repositories.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Transactional
    public MiembroInfoActDTO registrarCanjeRecompensa(@Valid CanjeRequestDTO canje) {

        List<DetalleCanjePost> detalleCanjePosts = canje.getDetallesCanje();

        if(detalleCanjePosts == null || detalleCanjePosts.isEmpty()) {
            throw new ServiceException(ErrorCode.CANJE_DETAIL_NULL.getCode() + ": " + ErrorCode.CANJE_DETAIL_NULL.getDescription());
        }

        Long puntosIni =puntosService.getTotalPuntosDisponiblesPorCliente(canje.getMiembroId());
        if(puntosIni < canje.getTotalPuntos()){
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

            entityManager.flush();
            entityManager.refresh(canjeEntity);

            int puntosAux = 0;

            List<RecompensaActInfoDTO> recActs = new ArrayList<>();

            List<DetalleCanjeEntity> detallesCanjeEntity = new ArrayList<>();

            for(DetalleCanjePost detalleCanjePost : canje.getDetallesCanje()){
                DetalleCanjeEntity detalleCanjeEntity = new DetalleCanjeEntity();

                RecompensaEntity r = recompensaRepository.findById(detalleCanjePost.getRecompensaId());

                if(detalleCanjePost.getCantidadRecompensa()<=r.getStock()){
                    detalleCanjeEntity.setDcjCanjePadre(canjeEntity);
                    detalleCanjeEntity.setCantidadRecompensa(detalleCanjePost.getCantidadRecompensa());
                    detalleCanjeEntity.setPuntosDetCanje(r.getPuntosRequeridos()* detalleCanjePost.getCantidadRecompensa());
                    detalleCanjeEntity.setDcjRecompensa(r);

                    recActs.add(new RecompensaActInfoDTO(r.getRecId(), r.getStock()-detalleCanjePost.getCantidadRecompensa()));

                    //con esto también se cancela todo un canje si es que una recompensa no tuvo stock
                    puntosAux += r.getPuntosRequeridos()*detalleCanjePost.getCantidadRecompensa();

                    detallesCanjeEntity.add(detalleCanjeEntity);
                } else {
                    recActs.add(new RecompensaActInfoDTO(r.getRecId(), r.getStock()));
                }

            }

            //verificación en caso haya habido un problema de sincronización frontend/backend -> se prioriza el backend
            //Verificación si hubo algún error en el registro de alguna recompensa
            if(puntosAux == canje.getTotalPuntos()){
                puntosService.canjearPuntos(canje.getMiembroId(), canje.getTotalPuntos());
                detalleCanjeRepository.persist(detallesCanjeEntity);
                for(RecompensaActInfoDTO rec : recActs){
                    recompensaRepository.findById(rec.getIdRec()).setStock(rec.getNewStock());
                }
            } else {
                canjeRepository.delete(canjeEntity);
                CanjeGetDTO canjeGetDTO = new CanjeGetDTO();
                canjeGetDTO.setRecsActualizadas(recActs);
                return new MiembroInfoActDTO(canjeGetDTO, puntosIni, puntosService.getTotalPuntosCercanosVencerPorCliente(canje.getMiembroId()));
            }

            Long newPuntos = puntosService.getTotalPuntosDisponiblesPorCliente(canje.getMiembroId());
            Long newPuntosVencer = puntosService.getTotalPuntosCercanosVencerPorCliente(canje.getMiembroId());

            List<DetalleRecompensaCanjeDTO> listaDetalles = detalleCanjeRepository.obtenerDetalleRecompensas(canjeEntity.getId());

            CanjeGetDTO canjeGetDTO = new CanjeGetDTO();

            canjeGetDTO.setIdCanje(canjeEntity.getId());
            canjeGetDTO.setDetallesCanje(listaDetalles);
            canjeGetDTO.setFechaCanje(canjeEntity.getFecha());
            canjeGetDTO.setTotalPuntos(puntosAux);
            canjeGetDTO.setRecsActualizadas(recActs);

            return new MiembroInfoActDTO(canjeGetDTO, newPuntos, newPuntosVencer);
        } catch(Exception e) {
            throw new ServiceException(ErrorCode.REGISTER_CANJE_FAILED.getCode()+ ": " + e.getMessage());
        }
    }

    public List<DetalleRecompensaCanjeDTO> ultimosCanjesCliente(Long miembroId, Long periodoId) {
        List<Long> canjesIds = canjeRepository.canjesPorPeriodo(periodoId, miembroId).stream().map(CanjeEntity::getId).toList();
        List<DetalleRecompensaCanjeDTO> detalleRecompensaCanjeDTOS = new ArrayList<>();

        for(Long idCanje : canjesIds){
            detalleRecompensaCanjeDTOS.addAll(detalleCanjeRepository.obtenerDetalleRecompensas(idCanje));
        }
        return  detalleRecompensaCanjeDTOS;
    }



    public void eliminarCanje(Long canjeId){
        CanjeEntity canjeEntity = canjeRepository.findById(canjeId);

        MiembroClubEntity miembroEntity = canjeEntity.getMiembro();


    }

    public List<CanjeResumenDTO> listarCanjes(LocalDate desde, LocalDate hasta, String tipo) {
        String jpql = "SELECT c FROM CanjeEntity c WHERE 1=1";
        Map<String, Object> params = new HashMap<>();

        if (desde != null) {
            jpql += " AND c.fecha >= :desde";
            params.put("desde", desde);
        }

        if (hasta != null) {
            jpql += " AND c.fecha <= :hasta";
            params.put("hasta", hasta);
        }

        if (tipo != null && !tipo.isBlank()) {
            jpql += " AND c.tipoCanje = :tipo";
            params.put("tipo", CanjeEntity.TipoCanje.valueOf(tipo.toUpperCase()));
        }

        TypedQuery<CanjeEntity> query = entityManager.createQuery(jpql + " ORDER BY c.fecha DESC", CanjeEntity.class);
        params.forEach(query::setParameter);

        return query.getResultList().stream().map(c -> {
            int totalPuntos = c.getDetallesCanje().stream()
                    .mapToInt(DetalleCanjeEntity::getPuntosDetCanje)
                    .sum();
            return new CanjeResumenDTO(c.getId(), c.getFecha(), c.getTipoCanje().name(), totalPuntos, c.getEstado().name());
        }).collect(Collectors.toList());
    }

    private class stockID{
        Integer stock;
        Long idRec;

    }


}
