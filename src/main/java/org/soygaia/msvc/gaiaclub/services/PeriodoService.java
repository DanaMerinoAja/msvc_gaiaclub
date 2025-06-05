package org.soygaia.msvc.gaiaclub.services;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.soygaia.msvc.gaiaclub.models.dtos.periodo.PeriodoCreationResponseDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.periodo.PeriodoDTO;
import org.soygaia.msvc.gaiaclub.models.entity.PeriodoEntity;
import org.soygaia.msvc.gaiaclub.repositories.PeriodoRepository;

import java.util.List;

@ApplicationScoped
@Transactional
public class PeriodoService {
    @Inject
    PeriodoRepository periodoRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public PeriodoCreationResponseDTO registrarPeriodo(PeriodoDTO dto) {
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
        periodo.setValorPunto(dto.getValorPunto());
        periodo.setDescripcion(dto.getDescripcion());
        periodo.setFechaInicio(dto.getFechaInicio());
        periodo.setFechaFin(dto.getFechaFin());
        periodoRepository.persist(periodo);

        return new PeriodoCreationResponseDTO(null, periodo, true);
    }

    public PeriodoEntity getCurrentPeriod() {
        return periodoRepository.findPeriodoActual();
    }

    public PeriodoEntity getNextPeriod() {
        return periodoRepository.findPeriodoProximo();
    }
}
