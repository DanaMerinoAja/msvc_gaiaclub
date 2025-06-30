package org.soygaia.msvc.gaiaclub.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.soygaia.msvc.gaiaclub.models.entity.PeriodoEntity;

import java.time.LocalDate;

@ApplicationScoped
@Transactional
public class PeriodoRepository implements PanacheRepository<PeriodoEntity> {
    public PeriodoEntity findPeriodoActual() {
        return find("SELECT p FROM PeriodoEntity p WHERE p.fechaInicio <= ?1 AND p.fechaFin >= ?1",
                LocalDate.now()).firstResult();
    }

    public PeriodoEntity findPeriodoProximo() {
        return find("SELECT p FROM PeriodoEntity p WHERE p.fechaInicio BETWEEN ?1 AND ?2 ORDER BY p.fechaInicio ASC",
                LocalDate.now(), LocalDate.now().plusWeeks(1))
                .firstResult();
    }
}
