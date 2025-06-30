package org.soygaia.msvc.gaiaclub.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.soygaia.msvc.gaiaclub.models.entity.MiembroClubEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.time.LocalDate;

@ApplicationScoped
@Transactional
public class MiembroRepository implements PanacheRepository<MiembroClubEntity> {
    // Total clientes registrados
    public long contarTotalMiembros() {
        return count();
    }

    // Clientes registrados por fecha (solo hoy, ayer, etc.)
    public long contarMiembrosPorFecha(LocalDate fecha) {
        return count("DATE(fechaRegistro) = ?1", fecha);
    }

    public long contarMiembrosEntreFechas(LocalDate fecha1, LocalDate fecha2) {
        return count("DATE(fechaRegistro) >= ?1 AND DATE(fechaRegistro) <= ?2", fecha1, fecha2);
    }

    // Clientes que han canjeado puntos en el periodo actual
    public long contarClientesConCanjes(Long periodoId) {
        return getEntityManager().createQuery("""
        SELECT COUNT(DISTINCT c.miembro.id)
        FROM CanjeEntity c
        WHERE c.periodo.id = :periodoId
    """, Long.class).setParameter("periodoId", periodoId).getSingleResult();
    }

    public MiembroClubEntity findByDNI(String dni){
        return find("dni=?1", dni).firstResult();
    }

}
