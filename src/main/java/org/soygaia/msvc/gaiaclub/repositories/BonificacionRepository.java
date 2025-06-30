package org.soygaia.msvc.gaiaclub.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.soygaia.msvc.gaiaclub.models.entity.BonificacionEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class BonificacionRepository implements PanacheRepository<BonificacionEntity> {

    public List<BonificacionEntity> findUltimasVersiones() {
        return getEntityManager().createQuery(
                "SELECT b FROM BonificacionEntity b " +
                        "WHERE b.id IN (" +
                        "   SELECT b2.id FROM BonificacionEntity b2 WHERE b2.activa = true" +
                        ") " +
                        "OR b.id IN (" +
                        "   SELECT b3.id FROM BonificacionEntity b3 " +
                        "   WHERE b3.fechaCreacion = (" +
                        "       SELECT MAX(b4.fechaCreacion) FROM BonificacionEntity b4 " +
                        "       WHERE b4.bonificacionOrigen.id = b3.bonificacionOrigen.id" +
                        "   ) " +
                        "   AND NOT EXISTS (" +
                        "       SELECT 1 FROM BonificacionEntity b5 " +
                        "       WHERE b5.bonificacionOrigen.id = b3.bonificacionOrigen.id AND b5.activa = true" +
                        "   )" +
                        ")",
                BonificacionEntity.class
        ).getResultList();
    }



}
