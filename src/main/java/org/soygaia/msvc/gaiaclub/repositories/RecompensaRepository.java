package org.soygaia.msvc.gaiaclub.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.soygaia.msvc.gaiaclub.models.dtos.RecompensaDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.RecompensaProductoDTO;
import org.soygaia.msvc.gaiaclub.models.entity.RecompensaEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
@Transactional
public class RecompensaRepository implements PanacheRepository<RecompensaEntity> {

    @Inject
    EntityManager entityManager;

    public List<RecompensaProductoDTO> findByPeriodo(Long periodoId){
        String sql = "SELECT\n" +
                "    rec.rec_id, \n" +
                "    rec.rec_aportesoles, \n" +
                "    rec.rec_descripcion,\n" +
                "    rec.rec_producto,\n" +
                "    ppv.pvt_preciomaximo,\n" +
                "    rec.rec_nombre, \n" +
                "    rec.rec_puntosreq,\n" +
                "    rec.rec_stock, \n" +
                "    rec.rec_periodo,\n" +
                "    prod.prd_nombre,\n" +
                "    prod.prd_slug,\n" +
                "    undmedpres.umd_abreviatura,\n" +
                "    undmed.umd_abreviatura,\n" +
                "    pres.ppd_nombre,\n" +
                "    prod.prd_sku,\n" +
                "    marc.mrc_nombre,\n" +
                "    prod.prd_cantidadmedida,\n" +
                "    STRING_AGG(img.img_url, ',' ORDER BY img.img_id ASC) AS imagen_urls\n" +
                "FROM gaiaadm.t_producto AS prod\n" +
                "JOIN gaiaadm.t_imagenproducto AS img ON prod.prd_id = img.img_producto\n" +
                "JOIN gaiaadm.t_precioventa AS ppv ON ppv.pvt_id = prod.prd_id\n" +
                "JOIN gaiaadm.t_unidadmedida AS undmedpres ON prod.prd_undmedpresentacion = undmedpres.umd_id\n" +
                "JOIN gaiaadm.t_unidadmedida AS undmed ON prod.prd_unidadmedida = undmed.umd_id\n" +
                "JOIN gaiaadm.t_recompensas AS rec ON rec.rec_producto = prod.prd_id\n" +
                "JOIN gaiaadm.t_presentacionproducto AS pres ON pres.ppd_id = prod.prd_presentacion\n" +
                "JOIN gaiaadm.t_marca AS marc ON marc.mrc_id = prod.prd_marca\n" +
                "WHERE rec.rec_periodo = :periodoId\n" +
                "GROUP BY\n" +
                "    rec.rec_id, \n" +
                "    prod.prd_sku,\n" +
                "    rec.rec_aportesoles,\n" +
                "    rec.rec_descripcion,\n" +
                "    rec.rec_producto, \n" +
                "\tppv.pvt_preciomaximo,\n" +
                "    rec.rec_nombre,\n" +
                "    rec.rec_puntosreq,\n" +
                "    rec.rec_stock,\n" +
                "    rec.rec_periodo,\n" +
                "    prod.prd_nombre,\n" +
                "    prod.prd_slug,\n" +
                "    undmedpres.umd_abreviatura,\n" +
                "    undmed.umd_abreviatura,\n" +
                "    pres.ppd_nombre,\n" +
                "    marc.mrc_nombre,\n" +
                "    prod.prd_cantidadmedida;";

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
                    (BigDecimal) row[4],
                    (String) row[5],  // rec_nombre
                    (Integer) row[6],  // rec_puntosreq
                    (Integer) row[7],  // rec_stock
                    (Long) row[8],  // rec_periodo
                    (String) row[9],  // prd_nombre
                    (String) row[10],  // prd_slug
                    (String) row[11], // umd_pres_abreviatura 450->ml<-
                    (String) row[12], // umd_venta S/.16 450ml ->UND<-
                    (String) row[13], // ppd_nombre
                    (String) row[14], // prd_sku
                    (String) row[15], //prd_marca
                    (String) row[16], //prd_cantidadmedida
                    (String) row[17]  // img_url
            );
            dto.setNombreRecompensa(dto.getNombreProducto() + " "+dto.getCantidadMedida() +" " + dto.getAbreviaturaUnidadMedidaPres() + " " + dto.getMarca());
            recompensasConProductos.add(dto);
        }

        return recompensasConProductos;
        //return find("periodo.id", periodoId).list();
    }
    public List<RecompensaEntity> recompensasDisponibles(Long periodoId) {
        return find("stock > 0 AND periodo.id", periodoId).list();
    }

    public RecompensaEntity nuevaRecompensa(RecompensaDTO recompensaDTO){
        return null;

    }
}
