package org.soygaia.msvc.gaiaclub.services;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.soygaia.msvc.gaiaclub.models.dtos.*;
import org.soygaia.msvc.gaiaclub.models.entity.RecompensaEntity;
import org.soygaia.msvc.gaiaclub.models.entity.RecompensaProductoEntity;
import org.soygaia.msvc.gaiaclub.models.entity.ValeEntity;
import org.soygaia.msvc.gaiaclub.repositories.PeriodoRepository;
import org.soygaia.msvc.gaiaclub.repositories.ProductoRepository;
import org.soygaia.msvc.gaiaclub.repositories.RecompensaRepository;
import org.soygaia.msvc.gaiaclub.repositories.ValeRepository;

import java.util.List;

@ApplicationScoped
@Transactional
public class RecompensaService {
    @Inject
    RecompensaRepository recompensaRepository;

    @Inject
    PeriodoRepository periodoRepository;

    @Inject
    ProductoRepository productoRepository;

    @Inject
    ValeRepository valeRepository;

    @PersistenceContext
    EntityManager entityManager;


    public RecompensaResponseDTO guardarRecompensa(RecompensaDTO recompensaDTO) {

        // Verificar duplicados
        List<RecompensaEntity> recompensasDuplicadas = recompensaRepository.find(
                "SELECT r FROM RecompensaEntity r WHERE r.periodoId = ?1 AND r.recompensaId = ?2 AND TYPE(r) = ?3",
                recompensaDTO.getIdPeriodo(),
                recompensaDTO.getIdRecompensa(),
                recompensaDTO.getTipoRecompensa().equals("VALE") ? ValeEntity.class : RecompensaProductoEntity.class
        ).list();

        if (!recompensasDuplicadas.isEmpty()) {
            return new RecompensaResponseDTO(recompensasDuplicadas, null, false);
        }

        // Crear la instancia según tipo
        RecompensaEntity recompensa;
        if (recompensaDTO.getTipoRecompensa().equals("VALE")) {
            ValeEntity vale = new ValeEntity();
            vale.setDescuentoSoles(recompensaDTO.getAporteSoles()); // mismo campo
            vale.setVigencia(recompensaDTO.getVigencia());
            vale.setNombre("Vale por " + recompensaDTO.getAporteSoles() + " soles de descuento");
            vale.setDescripcion("Vigencia por " + recompensaDTO.getVigencia() + " días desde el canje.");
            recompensa = vale;
        } else {
            RecompensaProductoEntity producto = new RecompensaProductoEntity();
            producto.setAporteSoles(recompensaDTO.getAporteSoles());
            producto.setProductoId(recompensaDTO.getIdRecompensa());
            producto.setNombre(recompensaDTO.getNombre());
            producto.setDescripcion(recompensaDTO.getDescripcion());
            recompensa = producto;
        }

        // Comunes para ambos
        recompensa.setPuntosRequeridos(recompensaDTO.getPuntosRequeridos());
        recompensa.setStock(recompensaDTO.getStock());
        recompensa.setPeriodoId(recompensaDTO.getIdPeriodo());

        recompensaRepository.persist(recompensa);
        return new RecompensaResponseDTO(null, recompensa, true);
    }


    public void eliminarRecompensa(RecompensaEntity recompensa){
        recompensaRepository.delete(recompensa);
    }

//    public void modificarRecompensa(RecompensaEntity recompensa, Long idRecomOrg){
//        RecompensaEntity recompensaEntity = recompensaRepository.findById(idRecomOrg);
//        if(recompensa.getRecompensa()!=null) recompensaEntity.setRecompensa(recompensa.getRecompensa());
//        if(!recompensa.getNombre().isBlank()) recompensaEntity.setNombre(recompensa.getNombre());
//        if(recompensa.getStock()>=0) recompensaEntity.setStock(recompensa.getStock());
//        if(!recompensa.getDescripcion().isBlank()) recompensaEntity.setDescripcion(recompensa.getDescripcion());
//
//    }

    public void agregarStock(Long idRecompensa, int addUnits){
        RecompensaEntity recompensa = recompensaRepository.findById(idRecompensa);
        recompensa.setStock(recompensa.getStock()+addUnits);
    }

    public void restarStock(Long idRecompensa, int minusUnits){
        RecompensaEntity recompensa = recompensaRepository.findById(idRecompensa);
        recompensa.setStock(recompensa.getStock()-minusUnits);
    }

//    public void modificarValorRecompensa(Long idRecompensa, double aporteSoles, int puntosRequeridos){
//        RecompensaEntity recompensa = recompensaRepository.findById(idRecompensa);
//        recompensa.setPuntosRequeridos(puntosRequeridos);
//        recompensa.setAporteSoles(aporteSoles);
//    }
//
//    public void modificarValorRecompensa(Long idRecompensa, double aporteSoles){
//        RecompensaEntity recompensa = recompensaRepository.findById(idRecompensa);
//        recompensa.setAporteSoles(aporteSoles);
//    }

    public void modificarValorRecompensa(Long idRecompensa, int puntosRequeridos){
        RecompensaEntity recompensa = recompensaRepository.findById(idRecompensa);
        recompensa.setPuntosRequeridos(puntosRequeridos);
    }

    public void disminuirStock(Long idRecompensa, int cantidad) {
        RecompensaEntity recompensa = recompensaRepository.findById(idRecompensa);
        if (recompensa != null && recompensa.getStock() >= cantidad) {
            recompensa.setStock(recompensa.getStock() - cantidad);
        } else {
            throw new IllegalArgumentException("Stock insuficiente o recompensa no encontrada");
        }
    }

    public RecompensasResponseDTOF recompensasPeriodo(Long idPeriodo){
        List<ValeEntity> vales = recompensaRepository.findValesPorPeriodo(idPeriodo);
        List<RecompensaValeDTO> valesDTO = vales.stream()
                .map(v -> new RecompensaValeDTO(v.getRecId(), v.getDescuentoSoles(), v.getNombre(), v.getDescripcion(), v.getVigencia(), v.getPuntosRequeridos(), v.getStock()))
                .toList();
        List<RecompensaProductoDTO> recompensaProductoDTOS = recompensaRepository.findProductosByPeriodo(idPeriodo);
        return new RecompensasResponseDTOF(recompensaProductoDTOS, valesDTO);
    }

    public List<RecompensaEntity> recompensasDisponibles(Long idPeriodo) {
        return recompensaRepository.recompensasDisponibles(idPeriodo);
    }

    public RecompensaEntity obtenerRecompensaValida(Long id) {
        RecompensaEntity recompensa = recompensaRepository.findById(id);
        if (recompensa == null) {
            throw new NotFoundException("Recompensa no encontrada");
        }
        if (recompensa.getStock() <= 0) {
            throw new IllegalStateException("Recompensa sin stock disponible");
        }
        return recompensa;
    }


}
