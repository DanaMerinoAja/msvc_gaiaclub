package org.soygaia.msvc.gaiaclub.services;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.panleadministracion.periodos.PeriodoCreationResponseDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.panleadministracion.periodos.PeriodoCreationDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.panleadministracion.periodos.PeriodoDTO;
import org.soygaia.msvc.gaiaclub.models.entity.PeriodoEntity;
import org.soygaia.msvc.gaiaclub.repositories.PeriodoRepository;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class PeriodoService {
    @Inject
    PeriodoRepository periodoRepository;

    @PersistenceContext
    EntityManager entityManager;

    public PeriodoCreationResponseDTO registrarPeriodo(PeriodoCreationDTO dto) {
        // Verificar que no haya periodos con fechas cruzadas
        List<PeriodoEntity> periodosCruzados = periodoRepository.find(
                "SELECT p FROM PeriodoEntity p WHERE " +
                        "(p.fechaInicio BETWEEN ?1 AND ?2) " +
                        "OR (p.fechaFin BETWEEN ?1 AND ?2) " +
                        "OR (?1 BETWEEN p.fechaInicio AND p.fechaFin) " +
                        "OR (?2 BETWEEN p.fechaInicio AND p.fechaFin)",
                dto.getFechaInicio(), dto.getFechaFin()
        ).list();

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

    public PeriodoEntity getCurrentPeriod() {
        return periodoRepository.findPeriodoActual();
    }

    public PeriodoEntity modificarPeriodo(PeriodoDTO periodoDTO){
        PeriodoEntity periodo = periodoRepository.findById(periodoDTO.getIdPeriodo());
        periodo.setDescripcion(periodoDTO.getDescripcion());
        periodo.setNombre(periodoDTO.getNombre());
        periodo.setFechaFin(periodoDTO.getFechaFin());
        periodo.setFechaInicio(periodoDTO.getFechaInicio());
        return periodo;
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

    public PeriodoEntity getNextPeriod() {
        return periodoRepository.findPeriodoProximo();
    }
}
