package org.soygaia.msvc.gaiaclub.services;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.soygaia.msvc.gaiaclub.repositories.CanjeRepository;
import org.soygaia.msvc.gaiaclub.repositories.DetalleCanjeRepository;
import org.soygaia.msvc.gaiaclub.repositories.RecompensaRepository;

@ApplicationScoped
@Transactional
public class DetalleCanjeService {
    @Inject
    DetalleCanjeRepository detalleCanjeRepository;

    @Inject
    CanjeRepository canjeRepository;

    @Inject
    RecompensaRepository recompensaRepository;


}
