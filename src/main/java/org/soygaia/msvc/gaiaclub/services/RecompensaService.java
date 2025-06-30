package org.soygaia.msvc.gaiaclub.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.panelcanjes.CanjeResumenDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.panleadministracion.recompensas.DeleteResponse;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.panleadministracion.recompensas.RecompensaPostDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.panleadministracion.recompensas.RecompensaPutDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.panleadministracion.recompensas.RecompensaResponseDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.productos.RecompensaProductoDTO;
import org.soygaia.msvc.gaiaclub.models.entity.PeriodoEntity;
import org.soygaia.msvc.gaiaclub.models.entity.RecompensaEntity;
import org.soygaia.msvc.gaiaclub.repositories.DetalleCanjeRepository;
import org.soygaia.msvc.gaiaclub.repositories.PeriodoRepository;
import org.soygaia.msvc.gaiaclub.repositories.RecompensaRepository;
import org.soygaia.msvc.gaiaclub.repositories.VistaRecompensaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class RecompensaService {

    @Inject
    RecompensaRepository recompensaRepository;

    @Inject
    PeriodoRepository periodoRepository;

    @Inject
    VistaRecompensaRepository vistaRecompensaRepository;

    @Inject
    DetalleCanjeRepository detalleCanjeRepository;

    @PersistenceContext
    EntityManager entityManager;

    // Guarda solo recompensas tipo PRODUCTO
    public RecompensaResponseDTO guardarRecompensa(RecompensaPostDTO recompensaPostDTO) {
        // Verificar duplicados
        Optional<RecompensaEntity> recompensaExiste = recompensaRepository.find(
                "SELECT r FROM RecompensaEntity r WHERE r.periodo.id = ?1 AND r.productoId = ?2",
                recompensaPostDTO.getIdPeriodo(),
                recompensaPostDTO.getIdProducto()
        ).firstResultOptional();

        if (recompensaExiste.isPresent()) {
            return new RecompensaResponseDTO(recompensaExiste.get(), null, false);
        }

        RecompensaEntity recompensaCreada = new RecompensaEntity();
        recompensaCreada.setAporteSoles(recompensaPostDTO.getAporteSoles());
        recompensaCreada.setProductoId(recompensaPostDTO.getIdProducto());
        recompensaCreada.setNombre(recompensaPostDTO.getNombre());
        recompensaCreada.setDescripcion(recompensaPostDTO.getDescripcion());
        recompensaCreada.setStock(recompensaPostDTO.getStock());
        recompensaCreada.setPuntosRequeridos(recompensaPostDTO.getPuntosRequeridos());
        recompensaCreada.setPeriodo(periodoRepository.findById(recompensaPostDTO.getIdPeriodo()));

        recompensaRepository.persist(recompensaCreada);
        return new RecompensaResponseDTO(null, recompensaCreada, true);
    }

    public RecompensaProductoDTO actualizarRecompensa(RecompensaPutDTO recompensaDTO){
        RecompensaEntity recompensaEntity = recompensaRepository.findById(recompensaDTO.getIdRecompensa());

        recompensaEntity.setStock(recompensaDTO.getStock());
        recompensaEntity.setNombre(recompensaDTO.getNombre());
        recompensaEntity.setDescripcion(recompensaDTO.getDescripcion());
        recompensaEntity.setAporteSoles(recompensaDTO.getAporteSoles());
        recompensaEntity.setPuntosRequeridos(recompensaDTO.getPuntosRequeridos());

        return vistaRecompensaRepository.findByIdRec(recompensaEntity.getRecId());
    }

    public RecompensaProductoDTO stockCero(Long id){
        RecompensaEntity recompensaEntity = recompensaRepository.findById(id);
        recompensaEntity.setStock(0);
        return vistaRecompensaRepository.findByIdRec(recompensaEntity.getRecId());
    }

    public DeleteResponse eliminarRecompensa(Long recompensaId) {
        RecompensaEntity recompensa = recompensaRepository.findById(recompensaId);

        if (recompensa == null) {
            return new DeleteResponse(-1L, "Recompensa no encontrada", false);
        }

        long cantidadCanjes = detalleCanjeRepository.count("dcjRecompensa.recId", recompensaId);

        if (cantidadCanjes > 0) {
            recompensa.setStock(0);
            return new DeleteResponse(recompensaId, "Tiene canjes registrados. Stock puesto en 0.", false);
        }

        recompensaRepository.deleteById(recompensaId);
        return new DeleteResponse(recompensaId, "Recompensa eliminada", true);
    }

    public List<RecompensaProductoDTO> recompensasPeriodoActual(){
        PeriodoEntity periodo = periodoRepository.findPeriodoActual();
        if(periodo != null){
            return vistaRecompensaRepository.findByPeriodo(periodo.getId());
        }
        return new ArrayList<>();
    }

    public List<RecompensaProductoDTO> recompensasPeriodo(Long idPeriodo){
        return vistaRecompensaRepository.findByPeriodo(idPeriodo);
    }

    public List<RecompensaEntity> recompensasDisponibles(Long idPeriodo) {
        return recompensaRepository.recompensasPeriodo(idPeriodo);
    }

    public List<RecompensaProductoDTO> listaAllRecompensasPaginado(int page, int size) {
        if(page>1 && (long) page *size > vistaRecompensaRepository.count()){
            return new ArrayList<>();
        }
        return vistaRecompensaRepository.findAllDTOs(page, size);
    }
}