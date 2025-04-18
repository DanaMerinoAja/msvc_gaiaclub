package org.soygaia.msvc.gaiaclub.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.soygaia.msvc.gaiaclub.models.entity.ValeEntity;

@ApplicationScoped
@Transactional
public class ValeRepository implements PanacheRepository<ValeEntity> {
    @Inject
    EntityManager entityManager;

    public ValeEntity findValeID(Long idVale){
        return findById(idVale);
    }
}
