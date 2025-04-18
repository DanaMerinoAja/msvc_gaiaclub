package org.soygaia.msvc.gaiaclub.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.soygaia.msvc.gaiaclub.models.entity.MiembroClubEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class MiembroRepository implements PanacheRepository<MiembroClubEntity> {
}
