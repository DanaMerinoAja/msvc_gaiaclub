package org.soygaia.msvc.gaiaclub.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.soygaia.msvc.gaiaclub.models.dtos.RecompensaProductoDTO;
import org.soygaia.msvc.gaiaclub.models.entity.RecompensaEntity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
@Transactional
public class RecompensaRepository implements PanacheRepository<RecompensaEntity> {

    @Inject
    EntityManager entityManager;

    public List<RecompensaProductoDTO> findByPeriodo(Long periodoId){
        String sql = "SELECT " +
                "    rec.rec_id, " +
                "    rec.rec_aportesoles, " +
                "    rec.rec_descripcion, " +
                "    rec.rec_producto, " +
                "    rec.rec_nombre, " +
                "    rec.rec_puntosreq, " +
                "    rec.rec_stock, " +
                "    rec.rec_periodo, " +
                "    prod.prd_nombre, " +
                "    prod.prd_slug, " +
                "    undmed.umd_abreviatura, " +
                "    undmed.umd_nombre, " +
                "    STRING_AGG(img.img_url, ',' ORDER BY img.img_id ASC) AS imagen_urls " + // Asegúrate de tener el espacio después de la coma
                "FROM gaiaadm.t_producto AS prod " + // Añadí un espacio al final
                "JOIN gaiaadm.t_imagenproducto AS img ON prod.prd_id = img.img_producto " + // Añadí un espacio al final
                "JOIN gaiaadm.t_unidadmedida AS undmed ON prod.prd_undmedpresentacion = undmed.umd_id " + // Añadí un espacio al final
                "JOIN gaiaadm.t_recompensas AS rec ON rec.rec_producto = prod.prd_id " + // Añadí un espacio al final
                "WHERE rec.rec_periodo = :periodoId " + // Usando nombre de parámetro para mayor claridad
                "GROUP BY " +
                "    rec.rec_id, " +
                "    rec.rec_aportesoles, " +
                "    rec.rec_descripcion, " +
                "    rec.rec_producto, " +
                "    rec.rec_nombre, " +
                "    rec.rec_puntosreq, " +
                "    rec.rec_stock, " +
                "    rec.rec_periodo, " +
                "    prod.prd_nombre, " +
                "    prod.prd_slug, " +
                "    undmed.umd_abreviatura, " +
                "    undmed.umd_nombre;";

        // Ejecutar la consulta nativa
        List<Object[]> result = entityManager.createNativeQuery(sql)
                .setParameter("periodoId", periodoId) // Establecer el periodoId con el nombre del parámetro
                .getResultList();


        // Mapear los resultados a una lista de DTOs
        List<RecompensaProductoDTO> recompensasConProductos = new ArrayList<>();
        for (Object[] row : result) {
            RecompensaProductoDTO dto = new RecompensaProductoDTO(
                    (Long) row[0],  // rec_id
                    (Double) row[1],  // rec_aportesoles
                    (String) row[2],  // rec_descripcion
                    (Long) row[3],  // rec_producto
                    (String) row[4],  // rec_nombre
                    (Integer) row[5],  // rec_puntosreq
                    (Integer) row[6],  // rec_stock
                    (Long) row[7],  // rec_periodo
                    (String) row[8],  // prd_nombre
                    (String) row[9],  // prd_slug
                    (String) row[10], // umd_abreviatura
                    (String) row[11], // umd_nombre
                    (String) row[12]  // img_url
            );
            recompensasConProductos.add(dto);
        }

        return recompensasConProductos;
        //return find("periodo.id", periodoId).list();
    }
    public List<RecompensaEntity> recompensasDisponibles(Long periodoId) {
        return find("stock > 0 AND periodo.id", periodoId).list();
    }

}
