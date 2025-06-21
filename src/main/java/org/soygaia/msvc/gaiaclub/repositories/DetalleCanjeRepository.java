package org.soygaia.msvc.gaiaclub.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.panelcanjes.DetalleRecompensaCanjeDTO;
import org.soygaia.msvc.gaiaclub.models.entity.DetalleCanjeEntity;
import org.soygaia.msvc.gaiaclub.models.entity.MiembroClubEntity;
import org.soygaia.msvc.gaiaclub.models.entity.PeriodoEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class DetalleCanjeRepository implements PanacheRepository<DetalleCanjeEntity> {
    public List<DetalleCanjeEntity> canjesPorPeriodo(PeriodoEntity periodo, MiembroClubEntity miembro) {

        return find("SELECT d FROM DetalleCanjeEntity d WHERE d.dcjCanjePadre.periodo = ?1 AND d.dcjCanjePadre.miembro = ?2",
                periodo, miembro).list();

        /*
        return getEntityManager()
                .createQuery("SELECT d FROM DetalleCanjeEntity d WHERE d.dcjCanjePadre.periodo.id = :periodoId AND d.dcjCanjePadre.miembro.id = :miembroId", DetalleCanjeEntity.class)
                .setParameter("periodoId", periodo.getId())
                .setParameter("miembroId", miembro.getIdMiembro())
                .getResultList();
*/
    }

    @PersistenceContext
    EntityManager entityManager;

    private double calcularPorcentajeDescuento(Double aporte, double original) {
        if (original == 0) return 0;
        return Math.round((original-aporte) * 100.0 / original);
    }

    public List<DetalleRecompensaCanjeDTO> obtenerDetalleRecompensas(Long idCanje) {
        String sql = """
            SELECT 
                (SELECT img_url FROM gaiaadm.t_imagenproducto 
                 WHERE img_producto = prod.prd_id 
                 ORDER BY img_id ASC 
                 LIMIT 1) AS imagen_url,
                 prod.prd_sku,
                 r.rec_nombre,
                 ppv.pvt_preciomaximo,
                 r.rec_aportesoles,
                 r.rec_puntosreq,
                 d.cj_cantidadrec
            FROM gaiaadm.t_detalles_canje AS d
            JOIN gaiaadm.t_recompensas AS r ON d.dcj_recompensa = r.rec_id
            JOIN gaiaadm.t_producto AS prod ON r.rec_producto = prod.prd_id
            JOIN gaiaadm.t_precioventa AS ppv ON ppv.pvt_producto = prod.prd_id
            WHERE d.dcj_canjePadre = :idCanje
        """;

        List<Object[]> rows = entityManager.createNativeQuery(sql)
                .setParameter("idCanje", idCanje)
                .getResultList();

        return rows.stream().map(r -> {
            String imagen = (String) r[0];
            String sku = (String) r[1];
            String nombre = (String) r[2];
            BigDecimal valorOriginal = (BigDecimal) r[3];
            Double aporteSoles = (Double) r[4];
            Integer puntos = (Integer) r[5];
            Integer cantidad = (Integer) r[6];

            return new DetalleRecompensaCanjeDTO(
                    imagen,
                    sku,
                    nombre,
                    puntos,
                    aporteSoles,
                    valorOriginal.doubleValue(),
                    calcularPorcentajeDescuento(aporteSoles, valorOriginal.doubleValue()),
                    cantidad
            );
        }).collect(Collectors.toList());

    }
}
