package org.soygaia.msvc.gaiaclub.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.soygaia.msvc.gaiaclub.models.entity.ValeClienteEntity;

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

    public List<ValeClienteEntity> findValesClientePeriodo(Long miembroClubID, Long periodoID) {
        return entityManager
                .createQuery("SELECT vc FROM ValeClienteEntity vc WHERE vc.fechaCaducidad > :fechaHoy AND vc.miembro.id = :miembro AND vc.estado = :estado AND vc.valePeriodo.periodo.id= :idPeriodo", ValeClienteEntity.class)
                .setParameter("fechaHoy", LocalDate.now())
                .setParameter("miembro", miembroClubID)
                .setParameter("estado", ValeClienteEntity.EstadoValeCliente.VIGENTE)
                .setParameter("idPeriodo", periodoID)
                .getResultList();
    }

    public long contarValesCanjeadosPeriodoEntreFechas(LocalDate fecha1, LocalDate fecha2) {
        return count("DATE(fechaCanje) >= ?1 AND DATE(fechaCanje)<=?2", fecha1, fecha2);
    }

    public long contarValesAplicadosPeriodoEntreFechas(LocalDate fecha1, LocalDate fecha2) {
        return count("DATE(fechaAplicacion) >= ?1 AND DATE(fechaAplicacion)<=?2", fecha1, fecha2);
    }

}
