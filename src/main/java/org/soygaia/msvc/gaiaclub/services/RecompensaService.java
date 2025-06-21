package org.soygaia.msvc.gaiaclub.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.RecompensaDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.RecompensaResponseDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.RecompensasResponseDTOF;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.productos.RecompensaProductoDTO;
import org.soygaia.msvc.gaiaclub.models.entity.PeriodoEntity;
import org.soygaia.msvc.gaiaclub.models.entity.RecompensaEntity;
import org.soygaia.msvc.gaiaclub.repositories.PeriodoRepository;
import org.soygaia.msvc.gaiaclub.repositories.ProductoRepository;
import org.soygaia.msvc.gaiaclub.repositories.RecompensaRepository;

import java.util.ArrayList;
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

    @PersistenceContext
    EntityManager entityManager;

    // Guarda solo recompensas tipo PRODUCTO
    public RecompensaResponseDTO guardarRecompensa(RecompensaDTO recompensaDTO) {
        // Verificar duplicados
        List<RecompensaEntity> recompensasDuplicadas = recompensaRepository.find(
                "SELECT r FROM RecompensaProductoEntity r WHERE r.periodo.id = ?1 AND r.productoId = ?2",
                recompensaDTO.getIdPeriodo(),
                recompensaDTO.getIdProducto()
        ).list();

        if (!recompensasDuplicadas.isEmpty()) {
            return new RecompensaResponseDTO(recompensasDuplicadas, null, false);
        }

        RecompensaEntity producto = new RecompensaEntity();
        producto.setAporteSoles(recompensaDTO.getAporteSoles());
        producto.setProductoId(recompensaDTO.getIdProducto());
        producto.setNombre(recompensaDTO.getNombre());
        producto.setDescripcion(recompensaDTO.getDescripcion());
        producto.setStock(recompensaDTO.getStock());
        producto.setPuntosRequeridos(recompensaDTO.getPuntosRequeridos());
        producto.setPeriodo(periodoRepository.findById(recompensaDTO.getIdPeriodo()));

        recompensaRepository.persist(producto);
        return new RecompensaResponseDTO(null, producto, true);
    }

    public void eliminarRecompensa(RecompensaEntity recompensa){
        recompensaRepository.delete(recompensa);
    }

    public void agregarStock(Long idRecompensa, int addUnits){
        RecompensaEntity recompensa = recompensaRepository.findById(idRecompensa);
        recompensa.setStock(recompensa.getStock()+addUnits);
    }

    public void restarStock(Long idRecompensa, int minusUnits){
        RecompensaEntity recompensa = recompensaRepository.findById(idRecompensa);
        recompensa.setStock(recompensa.getStock()-minusUnits);
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

    public List<RecompensaProductoDTO> recompensasPeriodo(){
        PeriodoEntity periodo = periodoRepository.findPeriodoActual();
        if(periodo != null){
            return recompensaRepository.findProductosByPeriodo(periodo.getId());
        }
        return new ArrayList<>();
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