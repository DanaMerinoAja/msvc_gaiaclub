package org.soygaia.msvc.gaiaclub.services;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.soygaia.msvc.gaiaclub.models.dtos.RecompensaDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.RecompensaProductoDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.RecompensaResponseDTO;
import org.soygaia.msvc.gaiaclub.models.entity.RecompensaEntity;
import org.soygaia.msvc.gaiaclub.repositories.PeriodoRepository;
import org.soygaia.msvc.gaiaclub.repositories.ProductoRepository;
import org.soygaia.msvc.gaiaclub.repositories.RecompensaRepository;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class RecompensaService {
    @Inject
    RecompensaRepository recompensaRepository;

    @Inject
    PeriodoRepository periodoRepository;

    @Inject
    ProductoRepository productoRepository;

    @PersistenceContext
    EntityManager entityManager;


    public RecompensaResponseDTO guardarRecompensa(RecompensaDTO recompensaDTO){
        RecompensaEntity recompensa = new RecompensaEntity();

        List<RecompensaEntity> recompensasDuplicadas = recompensaRepository.find(
                "SELECT r FROM RecompensaEntity r WHERE r.periodo.id = ?1 AND r.idProducto = ?2",
                recompensaDTO.getIdPeriodo(), recompensaDTO.getIdProducto()
        ).list();

        if(recompensasDuplicadas.isEmpty()){
            recompensa.setAporteSoles(recompensaDTO.getAporteSoles());
            recompensa.setPuntosRequeridos(recompensa.getPuntosRequeridos());
            recompensa.setIdProducto(recompensaDTO.getIdProducto());
            recompensa.setPuntosRequeridos(recompensaDTO.getPuntosRequeridos());
            recompensa.setStock(recompensaDTO.getStock());
            recompensa.setDescripcion(recompensaDTO.getDescripcion());
            recompensa.setNombre(recompensaDTO.getNombre());
            recompensa.setPeriodo(periodoRepository.findById(recompensaDTO.getIdPeriodo()));
            recompensaRepository.persist(recompensa);
            return new RecompensaResponseDTO(null, recompensa, true);
        }
        return new RecompensaResponseDTO(recompensasDuplicadas, null, false);
    }

    public void eliminarRecompensa(RecompensaEntity recompensa){
        recompensaRepository.delete(recompensa);
    }

    public void modificarRecompensa(RecompensaEntity recompensa, Long idRecomOrg){
        RecompensaEntity recompensaEntity = recompensaRepository.findById(idRecomOrg);
        if(recompensa.getIdProducto()!=null) recompensaEntity.setIdProducto(recompensa.getIdProducto());
        if(!recompensa.getNombre().isBlank()) recompensaEntity.setNombre(recompensa.getNombre());
        if(recompensa.getStock()>=0) recompensaEntity.setStock(recompensa.getStock());
        if(!recompensa.getDescripcion().isBlank()) recompensaEntity.setDescripcion(recompensa.getDescripcion());

    }

    public void agregarStock(Long idRecompensa, int addUnits){
        RecompensaEntity recompensa = recompensaRepository.findById(idRecompensa);
        recompensa.setStock(recompensa.getStock()+addUnits);
    }

    public void restarStock(Long idRecompensa, int minusUnits){
        RecompensaEntity recompensa = recompensaRepository.findById(idRecompensa);
        recompensa.setStock(recompensa.getStock()-minusUnits);
    }

    public void modificarValorRecompensa(Long idRecompensa, double aporteSoles, int puntosRequeridos){
        RecompensaEntity recompensa = recompensaRepository.findById(idRecompensa);
        recompensa.setPuntosRequeridos(puntosRequeridos);
        recompensa.setAporteSoles(aporteSoles);
    }

    public void modificarValorRecompensa(Long idRecompensa, double aporteSoles){
        RecompensaEntity recompensa = recompensaRepository.findById(idRecompensa);
        recompensa.setAporteSoles(aporteSoles);
    }

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

    public List<RecompensaProductoDTO> recompensasPeriodo(Long idPeriodo){
        return recompensaRepository.findByPeriodo(idPeriodo);
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
