package org.soygaia.msvc.gaiaclub.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.soygaia.msvc.gaiaclub.models.dtos.canjes.CanjeRequestDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.canjes.DetalleCanjePost;
import org.soygaia.msvc.gaiaclub.models.entity.ValeClienteEntity;
import org.soygaia.msvc.gaiaclub.models.entity.ValeEntity;
import org.soygaia.msvc.gaiaclub.repositories.MiembroRepository;
import org.soygaia.msvc.gaiaclub.repositories.RecompensaRepository;
import org.soygaia.msvc.gaiaclub.repositories.ValeClienteRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class ValesService {

    @Inject
    private MiembroRepository miembroRepository;
    @Inject
    private ValeClienteRepository valeClienteRepository;
    @Inject
    private RecompensaRepository valeRepository;
    @Inject
    private CanjeService canjeService;


    public List<ValeClienteEntity> valesPorCliente(Long idMiembroClub){
        return valeClienteRepository.findValesCliente(miembroRepository.findById(idMiembroClub));
    }

    public ValeClienteEntity guardarValeCliente(Long miembroId, Long valeId){
        ValeClienteEntity valeClienteEntity = new ValeClienteEntity();
        Optional<ValeEntity> valeEntityOp = valeRepository.findValeById(valeId);
        if(valeEntityOp.isPresent()){
            ValeEntity vale = valeEntityOp.get();
            CanjeRequestDTO canjeRequestDTO = getCanjeRequestDTO(miembroId, valeId, vale);

            canjeService.registrarCanje(canjeRequestDTO);

            valeClienteEntity.setVale(vale);
            valeClienteEntity.setFechaCaducidad(LocalDate.now().plusDays(vale.getVigencia()));
            valeClienteEntity.setMiembroClub(miembroRepository.findById(miembroId));
            valeClienteRepository.persist(valeClienteEntity);
        }
        return valeClienteEntity;
    }

    private static CanjeRequestDTO getCanjeRequestDTO(Long miembroId, Long valeId, ValeEntity vale) {
        CanjeRequestDTO canjeRequestDTO = new CanjeRequestDTO();

        canjeRequestDTO.setTotalPuntos(vale.getPuntosRequeridos());
        canjeRequestDTO.setMiembroId(miembroId);
        canjeRequestDTO.setPeriodoId(vale.getPeriodo().getId());

        DetalleCanjePost detalleCanjePost = new DetalleCanjePost();
        detalleCanjePost.setRecompensaId(valeId);
        detalleCanjePost.setPuntosDetCanje(vale.getPuntosRequeridos());
        detalleCanjePost.setCantidadRecompensa(1);

        canjeRequestDTO.setDetallesCanje(List.of(detalleCanjePost));
        return canjeRequestDTO;
    }
}
