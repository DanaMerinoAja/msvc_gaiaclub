package org.soygaia.msvc.gaiaclub.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.soygaia.msvc.gaiaclub.models.entity.MiembroClubEntity;
import org.soygaia.msvc.gaiaclub.models.entity.PeriodoEntity;
import org.soygaia.msvc.gaiaclub.models.entity.ValeClienteEntity;
import org.soygaia.msvc.gaiaclub.models.entity.ValePeriodoEntity;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
@Transactional
public class ValeClienteRepository implements PanacheRepository<ValeClienteEntity> {

    @Inject
    EntityManager entityManager;

    public List<ValeClienteEntity> findValesCliente(Long miembroClubID) {
        return entityManager
                .createQuery("SELECT vc FROM ValeClienteEntity vc WHERE vc.fechaCaducidad > :fechaHoy AND vc.miembro.id = :miembro AND vc.estado = :estado", ValeClienteEntity.class)
                .setParameter("fechaHoy", LocalDate.now())
                .setParameter("miembro", miembroClubID)
                .setParameter("estado", ValeClienteEntity.EstadoValeCliente.VIGENTE)
                .getResultList();
    }

}
