package org.soygaia.msvc.gaiaclub.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.canjes.CanjeRequestDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.canjes.DetalleCanjePost;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.vales.RecompensaValeDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.vales.RegistroValeClienteDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.vales.ValeClienteDTO;
import org.soygaia.msvc.gaiaclub.models.entity.MiembroClubEntity;
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

    public ValeClienteDTO guardarValeCliente(Long miembroId, Long valeId){
        ValeClienteEntity valeClienteEntity = new ValeClienteEntity();
        Optional<ValeEntity> valeEntityOp = valeRepository.findValeById(valeId);
        Optional<MiembroClubEntity> optionalMiembroClub = Optional.ofNullable(miembroRepository.findById(miembroId));
        if(valeEntityOp.isPresent() && optionalMiembroClub.isPresent()){
            ValeEntity vale = valeEntityOp.get();
            MiembroClubEntity miembroClub = optionalMiembroClub.get();

            //Se registra el canje del vale
            canjeService.registrarCanjeVale(vale, miembroClub);

            valeClienteEntity.setVale(vale);
            valeClienteEntity.setEstado(ValeClienteEntity.EstadoVale.VIGENTE);
            valeClienteEntity.setFechaCaducidad(LocalDate.now().plusDays(vale.getVigencia()));
            valeClienteEntity.setMiembroClub(miembroClub);
            valeClienteRepository.persist(valeClienteEntity);
        }
        return converToDTO(valeClienteEntity);
    }

    private CanjeRequestDTO getCanjeRequestDTO(Long miembroId, Long valeId, ValeEntity vale) {
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

    public ValeClienteDTO canjearVale(RegistroValeClienteDTO valeClienteDTO){
        ValeClienteEntity vc = valeClienteRepository.findById(valeClienteDTO.getValeid());
        vc.setEstado(ValeClienteEntity.EstadoVale.CANJEADO);
        return converToDTO(vc);
    }

    //metod de conversión para devolución
    public ValeClienteDTO converToDTO(ValeClienteEntity v){
        ValeClienteDTO vcDTO = new ValeClienteDTO();
        vcDTO.setIdMiembro(v.getMiembroClub().getIdMiembro());
        vcDTO.setIdRecompensa(v.getVale().getRecId());
        vcDTO.setIdVale(v.getId());
        ValeEntity vale = (ValeEntity) v.getVale();
        vcDTO.setVale(new RecompensaValeDTO(vale.getRecId(), vale.getDescuentoSoles(), vale.getNombre(), vale.getDescripcion(), vale.getVigencia(), vale.getPuntosRequeridos()));
        vcDTO.setFechaCaducidad(v.getFechaCaducidad());
        return vcDTO;
    }
}
