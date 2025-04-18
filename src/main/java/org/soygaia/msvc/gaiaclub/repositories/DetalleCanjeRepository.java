package org.soygaia.msvc.gaiaclub.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.soygaia.msvc.gaiaclub.models.entity.DetalleCanjeEntity;

import java.util.List;

@ApplicationScoped
@Transactional
public class DetalleCanjeRepository implements PanacheRepository<DetalleCanjeEntity> {
    public List<DetalleCanjeEntity> canjesPorPeriodo(Long periodoId, Long miembroId) {
        return find("SELECT d FROM CanjeEntity c JOIN c.detallesCanje d WHERE c.periodo.id = :periodoId AND c.miembro = :miembroId",
                "periodoId", periodoId,
                "miembroId", miembroId).list();
    }
}
