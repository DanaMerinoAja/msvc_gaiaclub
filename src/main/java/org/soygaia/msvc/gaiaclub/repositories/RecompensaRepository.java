package org.soygaia.msvc.gaiaclub.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.soygaia.msvc.gaiaclub.models.entity.RecompensaEntity;

import java.util.List;
import java.util.Optional;


@ApplicationScoped
@Transactional
public class RecompensaRepository implements PanacheRepository<RecompensaEntity> {

    public List<RecompensaEntity> recompensasPeriodo(Long periodoId) {
        return find("stock > 0 AND periodo.id", periodoId).list();
    }

    public long totalRecompensasPeriodo(Long periodoId) {
        return count("periodo.id", periodoId);
    }

    public long recompensasDisponiblesPeriodo(Long periodoId) {
        return count("periodo.id = ?1 AND stock > 0", periodoId);
    }

    public long recompensasSinStock(Long periodoId) {
        return count("periodo.id = ?1 AND stock = 0", periodoId);
    }

    public long recompensasPocoStock(Long periodoId, int limite) {
        return count("periodo.id = ?1 AND stock <= ?2 AND stock > 0", periodoId, limite);
    }

    public long recompensasStockTotalActual(Long periodoId) {
        return getEntityManager().createQuery("SELECT COALESCE(SUM(r.stock), 0) FROM RecompensaEntity r WHERE r.periodo.id = :periodoId", Long.class)
                .setParameter("periodoId", periodoId).getSingleResult();
    }

}
