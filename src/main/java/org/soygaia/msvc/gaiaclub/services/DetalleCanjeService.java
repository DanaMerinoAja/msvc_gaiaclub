package org.soygaia.msvc.gaiaclub.services;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.soygaia.msvc.gaiaclub.repositories.CanjeRepository;
import org.soygaia.msvc.gaiaclub.repositories.DetalleCanjeRepository;

@ApplicationScoped
@Transactional
public class DetalleCanjeService {
    @Inject
    DetalleCanjeRepository detalleCanjeRepository;

    @PersistenceContext
    EntityManager entityManager;
}
