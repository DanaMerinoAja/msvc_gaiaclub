package org.soygaia.msvc.gaiaclub.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.soygaia.msvc.gaiaclub.models.dtos.OrdenDTO;

@ApplicationScoped
@Transactional
public class OrdenRepository {

    @Inject
    EntityManager entityManager;

    public OrdenDTO findOrdenId(Long idOrden) {

        String sql = "SELECT \n" +
                "    o.ord_id, \n" +
                "    o.ord_cliente, \n" +
                "    o.ord_fechacreacion, \n" +
                "    SUM(od.odd_cantidad * od.odd_precio) AS total\n" +
                "FROM gaiaadm.t_orden AS o\n" +
                "JOIN gaiaadm.t_orden_detalle AS od ON o.ord_id = od.odd_orden\n" +
                "WHERE o.ord_id = :compraId\n"+
                "GROUP BY \n" +
                "    o.ord_id, \n" +
                "    o.ord_cliente, \n" +
                "    o.ord_fechacreacion;\n";

        return  (OrdenDTO) entityManager.createNativeQuery(sql, OrdenDTO.class)
                .setParameter("compraId", idOrden) // Establecer el periodoId con el nombre del parámetro
                .getSingleResult();
    }
}
