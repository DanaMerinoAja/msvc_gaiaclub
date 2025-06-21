package org.soygaia.msvc.gaiaclub.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.soygaia.msvc.gaiaclub.models.entity.ValeEntity;

@ApplicationScoped
@Transactional
public class ValeRepository implements PanacheRepository<ValeEntity> {
}
