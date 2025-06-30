package org.soygaia.msvc.gaiaclub.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.soygaia.msvc.gaiaclub.models.entity.PuntosEntity;

import java.time.LocalDate;

@ApplicationScoped
@Transactional
public class PuntosRepository implements PanacheRepository<PuntosEntity> {

    public long totalPuntos() {
        return getEntityManager().createQuery("SELECT COALESCE(SUM(p.totalPuntos), 0) FROM PuntosEntity p", Long.class)
                .getSingleResult();
    }

    public long totalPuntosEmitidodHoy() {
        return getEntityManager().createQuery("SELECT COALESCE(SUM(p.totalPuntos), 0) FROM PuntosEntity p WHERE p.estado='VIGENTE' AND p.fechaEmision=:hoy", Long.class)
                .setParameter("hoy", LocalDate.now())
                .getSingleResult();
    }

    public long totalPuntosCanjeadosHoy() {
        return getEntityManager().createQuery("SELECT COALESCE(SUM(p.totalPuntos), 0) FROM PuntosEntity p WHERE p.estado='CANJEADO' AND p.fechaCanje=:hoy", Long.class)
                .setParameter("hoy", LocalDate.now())
                .getSingleResult();
    }

    public long totalPuntosCanjeados() {
        return getEntityManager().createQuery("SELECT COALESCE(SUM(p.puntosCanjeados), 0) FROM PuntosEntity p WHERE p.estado='CANJEADO'", Long.class)
                .getSingleResult();
    }

    public long totalPuntosVencidos() {
        return getEntityManager().createQuery("SELECT COALESCE(SUM(p.totalPuntos), 0) FROM PuntosEntity p WHERE p.estado='CADUCADO'", Long.class)
                .getSingleResult();
    }

    public long puntosEmitidosEntreFechas(LocalDate fecha1, LocalDate fecha2) {
        return getEntityManager().createQuery("SELECT COALESCE(SUM(p.totalPuntos), 0) FROM PuntosEntity p WHERE p.estado='VIGENTE' AND DATE(p.fechaEmision) >= :fecha1" +
                        " AND DATE(p.fechaEmision) <= :fecha2", Long.class)
                .setParameter("fecha1", fecha1)
                .setParameter("fecha2", fecha2)
                .getSingleResult();
    }

    public long puntosCanjeadosEntreFechas(LocalDate fecha1, LocalDate fecha2) {
        return getEntityManager().createQuery("SELECT COALESCE(SUM(p.puntosCanjeados), 0) FROM PuntosEntity p WHERE DATE(p.fechaCanje) >= :fecha1" +
                        " AND DATE(p.fechaCanje) <= :fecha2 AND p.estado='CANJEADO'", Long.class)
                .setParameter("fecha1", fecha1)
                .setParameter("fecha2", fecha2)
                .getSingleResult();
    }

    public long puntosVencidosEntreFechas(LocalDate fecha1, LocalDate fecha2) {
        return getEntityManager().createQuery("SELECT COALESCE(SUM(p.totalPuntos), 0) FROM PuntosEntity p WHERE DATE(p.fechaCaducidad) >= :fecha1" +
                        " AND DATE(p.fechaCaducidad) <= :fecha2 AND p.estado='CADUCADO'", Long.class)
                .setParameter("fecha1", fecha1)
                .setParameter("fecha2", fecha2)
                .getSingleResult();
    }

    public long puntosBonificacionesEntreFechas(LocalDate fecha1, LocalDate fecha2) {
        return getEntityManager().createQuery("SELECT COALESCE(SUM(p.totalPuntos), 0) FROM PuntosEntity p WHERE DATE(p.fechaCaducidad) >= :fecha1" +
                        " AND DATE(p.fechaCaducidad) <= :fecha2 AND p.tipoOrigen=:tipoOrigen", Long.class)
                .setParameter("fecha1", fecha1)
                .setParameter("fecha2", fecha2)
                .setParameter("tipoOrigen", PuntosEntity.TipoOrigen.BONIFICACION)
                .getSingleResult();
    }
}
