package org.soygaia.msvc.gaiaclub.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.panelcanjes.CanjeResumenDTO;
import org.soygaia.msvc.gaiaclub.models.entity.CanjeEntity;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
@Transactional
public class CanjeRepository implements PanacheRepository<CanjeEntity> {

    @PersistenceContext
    EntityManager entityManager;

    public List<CanjeEntity> canjesPorPeriodo(Long periodoId, Long miembroId) {
        return find("SELECT c FROM CanjeEntity c WHERE c.periodo.id = ?1 AND c.miembro.id = ?2", periodoId, miembroId).list();
    }

    public List<CanjeResumenDTO> canjesPorEntregarResumen() {
        return entityManager.createQuery("""
                            SELECT new org.soygaia.msvc.gaiaclub.models.dtos.admin.panelcanjes.CanjeResumenDTO(
                                c.id,
                                m.nombresCompletos,
                                m.id,
                                c.fecha,
                                SUM(d.puntosDetCanje),
                                c.estado
                            )
                            FROM CanjeEntity c
                            JOIN c.miembro m
                            JOIN c.detallesCanje d
                            WHERE c.estado = 'POR_ENTREGAR'
                            GROUP BY c.id, m.nombresCompletos, m.id, c.fecha, c.estado
                        """, CanjeResumenDTO.class).getResultList();
    }

    public long totalCanjes() {
        return count();
    }

    public long canjesPorFecha(LocalDate fecha) {
        return count("DATE(fecha) = ?1", fecha);
    }

    public long totalCanjesPeriodo(Long periodoId) {
        return count("periodo.id", periodoId);
    }

    public long puntosCanjeadosPeriodo(Long periodoId) {
        return getEntityManager().createQuery("SELECT COALESCE(SUM(d.puntosDetCanje), 0) FROM CanjeEntity c JOIN c.detallesCanje d WHERE c.periodo.id = :periodoId", Long.class)
                .setParameter("periodoId", periodoId).getSingleResult();
    }

    public long recompensasCanjeadasPeriodo(Long periodoId) {
        return getEntityManager().createQuery("SELECT COALESCE(SUM(d.cantidadRecompensa), 0) FROM CanjeEntity c JOIN c.detallesCanje d WHERE c.periodo.id = :periodoId", Long.class)
                .setParameter("periodoId", periodoId).getSingleResult();
    }

    public long recompensasCanjeadasEntreFechas(LocalDate fecha1, LocalDate fecha2) {
        return getEntityManager().createQuery("SELECT COALESCE(SUM(d.cantidadRecompensa), 0) FROM CanjeEntity c JOIN c.detallesCanje d WHERE c.fecha >= :fecha1 AND c.fecha <= :fecha2", Long.class)
                .setParameter("fecha1", fecha1)
                .setParameter("fecha2", fecha2).getSingleResult();
    }

    public long totalCanjesEntreFechas(LocalDate fecha1, LocalDate fecha2) {
        return count("fecha >= ?1 AND fecha <= ?2", fecha1, fecha2);
    }

    public long puntosCanjeadosEntreFechas(LocalDate fecha1, LocalDate fecha2) {
        return getEntityManager().createQuery("SELECT COALESCE(SUM(d.puntosDetCanje), 0) FROM CanjeEntity c JOIN c.detallesCanje d WHERE c.fecha >= :fecha1 AND c.fecha <= :fecha2", Long.class)
                .setParameter("fecha1", fecha1)
                .setParameter("fecha2", fecha2).getSingleResult();
    }
}
