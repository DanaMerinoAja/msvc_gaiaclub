package org.soygaia.msvc.gaiaclub.services;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.panelcanjes.DetalleRecompensaCanjeDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.panelcanjes.DetalleValeCanjeDTO;
import org.soygaia.msvc.gaiaclub.models.entity.CanjeEntity;
import org.soygaia.msvc.gaiaclub.models.entity.ValePeriodoEntity;
import org.soygaia.msvc.gaiaclub.repositories.CanjeRepository;
import org.soygaia.msvc.gaiaclub.repositories.DetalleCanjeRepository;
import org.soygaia.msvc.gaiaclub.repositories.RecompensaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
