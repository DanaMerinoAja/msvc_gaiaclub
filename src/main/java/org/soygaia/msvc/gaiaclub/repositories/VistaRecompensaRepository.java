package org.soygaia.msvc.gaiaclub.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.productos.RecompensaProductoDTO;
import org.soygaia.msvc.gaiaclub.models.vistas.VistaRecompense;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class VistaRecompensaRepository implements PanacheRepository<VistaRecompense> {
    @PersistenceContext
    private EntityManager entityManager;

    public List<RecompensaProductoDTO> findByPeriodo(Long periodoId) {
        String sql = "SELECT " +
                "rec_id, rec_aportesoles, rec_descripcion, rec_producto, " +
                "pvt_preciomaximo, rec_nombre, rec_puntosreq, rec_stock, rec_periodo, " +
                "prd_nombre, prd_slug, umd_pres_abreviatura, umd_abreviatura, " +
                "ppd_nombre, prd_sku, mrc_nombre, prd_cantidadmedida, imagen_urls " +
                "FROM gaiaadm.v_recompensas_productos_periodo " +
                "WHERE rec_periodo = :periodoId";

        List<Object[]> results = entityManager.createNativeQuery(sql)
                .setParameter("periodoId", periodoId)
                .getResultList();

        return results.stream().map(row -> new RecompensaProductoDTO(
                (Long) row[0],                           // rec_id
                (Double) row[1],                         // rec_aportesoles
                (String) row[2],                         // rec_descripcion
                (Long) row[3],                           // rec_producto
                (BigDecimal) row[4],                     // pvt_preciomaximo
                (String) row[5],                         // rec_nombre
                (Integer) row[6],                        // rec_puntosreq
                (Integer) row[7],                        // rec_stock
                (Long) row[8],                           // rec_periodo
                (String) row[9],                         // prd_nombre
                (String) row[10],                        // prd_slug
                (String) row[11],                        // umd_pres_abreviatura
                (String) row[12],                        // umd_abreviatura
                (String) row[13],                        // ppd_nombre
                (String) row[14],                        // prd_sku
                (String) row[15],                        // mrc_nombre
                (String) row[16],                        // prd_cantidadmedida
                (String) row[17] // imagen_urls → List<String>
        )).collect(Collectors.toList());
    }

    public RecompensaProductoDTO findByIdRec(Long recompensaId) {
        String sql = "SELECT " +
                "rec_id, rec_aportesoles, rec_descripcion, rec_producto, " +
                "pvt_preciomaximo, rec_nombre, rec_puntosreq, rec_stock, rec_periodo, " +
                "prd_nombre, prd_slug, umd_pres_abreviatura, umd_abreviatura, " +
                "ppd_nombre, prd_sku, mrc_nombre, prd_cantidadmedida, imagen_urls " +
                "FROM gaiaadm.v_recompensas_productos_periodo " +
                "WHERE rec_id = :recompensaId";

        List<Object[]> result = entityManager.createNativeQuery(sql)
                .setParameter("recompensaId", recompensaId)
                .getResultList();

        Object[] row = result.getFirst();
        return  new RecompensaProductoDTO(
                    (Long) row[0],                           // rec_id
                    (Double) row[1],                         // rec_aportesoles
                    (String) row[2],                         // rec_descripcion
                    (Long) row[3],                           // rec_producto
                    (BigDecimal) row[4],                     // pvt_preciomaximo
                    (String) row[5],                         // rec_nombre
                    (Integer) row[6],                        // rec_puntosreq
                    (Integer) row[7],                        // rec_stock
                    (Long) row[8],                           // rec_periodo
                    (String) row[9],                         // prd_nombre
                    (String) row[10],                        // prd_slug
                    (String) row[11],                        // umd_pres_abreviatura
                    (String) row[12],                        // umd_abreviatura
                    (String) row[13],                        // ppd_nombre
                    (String) row[14],                        // prd_sku
                    (String) row[15],                        // mrc_nombre
                    (String) row[16],                        // prd_cantidadmedida
                    (String) row[17]                         // imagen_urls → List<String>
            );
    }
}
