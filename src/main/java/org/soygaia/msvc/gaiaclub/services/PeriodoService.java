package org.soygaia.msvc.gaiaclub.services;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.panleadministracion.periodos.PeriodoCreationResponseDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.panleadministracion.periodos.PeriodoCreationDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.panleadministracion.periodos.PeriodoDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.panleadministracion.recompensas.DeleteResponse;
import org.soygaia.msvc.gaiaclub.models.entity.PeriodoEntity;
import org.soygaia.msvc.gaiaclub.models.entity.RecompensaEntity;
import org.soygaia.msvc.gaiaclub.repositories.PeriodoRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class PeriodoService {
    @Inject
    PeriodoRepository periodoRepository;

    @Inject
    RecompensaService recompensaService;

    @PersistenceContext
    EntityManager entityManager;

    public PeriodoCreationResponseDTO registrarPeriodo(PeriodoCreationDTO dto) {
        // Verificar que no haya periodos con fechas cruzadas
        List<PeriodoEntity> periodosCruzados = periodosCruzados(dto.getFechaInicio(), dto.getFechaFin());

        if (!periodosCruzados.isEmpty()) {
            return new PeriodoCreationResponseDTO(periodosCruzados, null, false);
        }

        // Si no hay conflictos, crear el nuevo periodo
        PeriodoEntity periodo = new PeriodoEntity();
        periodo.setNombre(dto.getNombre());
        periodo.setDescripcion(dto.getDescripcion());
        periodo.setFechaInicio(dto.getFechaInicio());
        periodo.setFechaFin(dto.getFechaFin());
        periodoRepository.persist(periodo);

        return new PeriodoCreationResponseDTO(null, periodo, true);
    }

    public PeriodoCreationResponseDTO modificarPeriodo(PeriodoCreationDTO periodoDTO, Long periodoID){
        PeriodoEntity periodo = periodoRepository.findById(periodoID);

        List<PeriodoEntity> periodosCruzados = periodoRepository.find(
                "SELECT p FROM PeriodoEntity p WHERE " +
                        "(" +
                        "   (p.fechaInicio BETWEEN ?1 AND ?2) " +
                        "OR (p.fechaFin BETWEEN ?1 AND ?2) " +
                        "OR (?1 BETWEEN p.fechaInicio AND p.fechaFin) " +
                        "OR (?2 BETWEEN p.fechaInicio AND p.fechaFin) " +
                        ") AND p.id != ?3",
                periodo.getFechaInicio(), periodo.getFechaFin(), periodoID
        ).list();

        if (!periodosCruzados.isEmpty()) {
            return new PeriodoCreationResponseDTO(periodosCruzados, null, false);
        }

        periodo.setDescripcion(periodoDTO.getDescripcion());
        periodo.setNombre(periodoDTO.getNombre());
        periodo.setFechaFin(periodoDTO.getFechaFin());
        periodo.setFechaInicio(periodoDTO.getFechaInicio());

        return new PeriodoCreationResponseDTO(null, periodo, true);
    }

    private List<PeriodoEntity> periodosCruzados(LocalDate fechaInicio, LocalDate fechaFin){
        return periodoRepository.find(
                "SELECT p FROM PeriodoEntity p WHERE " +
                        "(p.fechaInicio BETWEEN ?1 AND ?2) " +
                        "OR (p.fechaFin BETWEEN ?1 AND ?2) " +
                        "OR (?1 BETWEEN p.fechaInicio AND p.fechaFin) " +
                        "OR (?2 BETWEEN p.fechaInicio AND p.fechaFin)",
                fechaInicio, fechaFin
        ).list();
    }

    public PeriodoEntity getCurrentPeriod() {
        return periodoRepository.findPeriodoActual();
    }

    public PeriodoEntity getNextPeriod() {
        return periodoRepository.findPeriodoProximo();
    }

    public List<PeriodoDTO> findAll(){
        List<PeriodoEntity> periodoEntities = periodoRepository.findAll().stream().toList();
        return periodoEntities.stream().map(periodo -> new PeriodoDTO(
                periodo.getFechaFin(),
                periodo.getFechaInicio(),
                periodo.getDescripcion(),
                periodo.getNombre(),
                periodo.getId())
        ).collect(Collectors.toList());
    }

    public DeleteResponse eliminarPeriodo(Long periodoId){
        PeriodoEntity periodo = periodoRepository.findById(periodoId);

        if(periodo.getFechaInicio().isBefore(LocalDate.now())){
            try{
                periodoRepository.delete(periodo);
                return new DeleteResponse(periodoId, "Periodo eliminado correctamente.", true);
            } catch (Exception ex){
                return new DeleteResponse(periodoId, "No se pudo eliminar el periodo. " + ex.getMessage(), false);
            }
        } else {
            boolean eliminar =true;
            List<RecompensaEntity> recs = periodo.getRecompensaList();
            List<RecompensaEntity> aux = periodo.getRecompensaList();
            for(RecompensaEntity rec : recs){
                DeleteResponse delRec = recompensaService.eliminarRecompensa(rec.getRecId());
                if(!delRec.isEliminado()){
                    eliminar = false;
                    break;
                }
            }
            if(!eliminar){
                periodo.setRecompensaList(aux);
                return new DeleteResponse(periodoId, "No se pudo eliminar el periodo porque tiene recompensas canjeadas. Pruebe inabilitar el periodo.", false);
            }
            return new DeleteResponse(periodoId, "Periodo eliminado correctamente.", true);
        }

    }
}
