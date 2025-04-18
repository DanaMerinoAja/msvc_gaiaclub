package org.soygaia.msvc.gaiaclub.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.soygaia.msvc.gaiaclub.models.entity.ecommerce.PrecioVentaReadOnlyEntity;
import org.soygaia.msvc.gaiaclub.models.entity.ecommerce.ProductoReadOnlyEntity;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PrecioVentaRepository implements PanacheRepository<PrecioVentaReadOnlyEntity> {
    @Inject
    EntityManager em;

    public List<PrecioVentaReadOnlyEntity> findAllByProductoIds(List<Long> ids) {
        return find("pvtProducto.id IN ?1", ids).list();
    }
}
