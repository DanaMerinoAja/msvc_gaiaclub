package org.soygaia.msvc.gaiaclub.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.soygaia.msvc.gaiaclub.models.entity.PuntosEntity;

@ApplicationScoped
@Transactional
public class PuntosRepository implements PanacheRepository<PuntosEntity> {

    @Inject
    EntityManager entityManager;

}
