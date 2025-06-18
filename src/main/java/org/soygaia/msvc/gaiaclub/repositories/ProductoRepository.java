package org.soygaia.msvc.gaiaclub.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.ecommerce.ProductoDTO;

import java.util.List;

@ApplicationScoped
@Transactional
public class ProductoRepository {
    @Inject
    EntityManager entityManager;

    public List<ProductoDTO> findProductosByIds(List<Long> productoIds) {
        return entityManager.createQuery(
                        "SELECT new ProductoDTO(p.id, p.nombre, p.descripcion) " +
                                "FROM Producto p WHERE p.id IN :productoIds", ProductoDTO.class)
                .setParameter("productoIds", productoIds)
                .getResultList();
    }
}
