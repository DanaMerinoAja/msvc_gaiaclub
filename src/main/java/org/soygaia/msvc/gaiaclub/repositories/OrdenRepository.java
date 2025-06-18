package org.soygaia.msvc.gaiaclub.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.ecommerce.OrdenDTO;

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

        Object[] result = (Object[]) entityManager.createNativeQuery(sql)
                .setParameter("compraId", idOrden)
                .getSingleResult();
        OrdenDTO dto = new OrdenDTO();
        dto.setId(((Number) result[0]).longValue());
        dto.setId_cliente(((Number) result[1]).longValue());
        dto.setFecha(((java.sql.Timestamp) result[2]).toLocalDateTime().toLocalDate()); // 👈 solución
        dto.setTotal(((Number) result[3]).doubleValue());

        //return new OrdenDTO((Long) result[0], (Long) result[1], ((java.sql.Timestamp) result[2]).toLocalDateTime().toLocalDate(), (Double) result[3]);
        return dto;
    }
}
