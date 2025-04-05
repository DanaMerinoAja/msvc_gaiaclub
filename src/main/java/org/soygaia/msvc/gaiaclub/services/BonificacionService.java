package org.soygaia.msvc.gaiaclub.services;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.soygaia.msvc.gaiaclub.repositories.BonificacionRepository;

@ApplicationScoped
@Transactional
public class BonificacionService {
    @Inject
    BonificacionRepository bonificacionRepository;

    @PersistenceContext
    EntityManager entityManager;


}
