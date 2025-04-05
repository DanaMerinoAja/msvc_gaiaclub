package org.soygaia.msvc.gaiaclub.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.soygaia.msvc.gaiaclub.models.entity.BonificacionEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class BonificacionRepository implements PanacheRepository<BonificacionEntity> {
}
