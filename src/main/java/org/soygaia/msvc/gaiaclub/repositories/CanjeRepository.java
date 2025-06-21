package org.soygaia.msvc.gaiaclub.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.soygaia.msvc.gaiaclub.models.entity.CanjeEntity;

import java.util.List;

@ApplicationScoped
@Transactional
public class CanjeRepository implements PanacheRepository<CanjeEntity> {
    public List<CanjeEntity> canjesPorPeriodo(Long periodoId, Long miembroId) {
        return find("SELECT c FROM CanjeEntity c WHERE c.periodo.id = ?1 AND c.miembro.id = ?2", periodoId, miembroId).list();
    }


}
