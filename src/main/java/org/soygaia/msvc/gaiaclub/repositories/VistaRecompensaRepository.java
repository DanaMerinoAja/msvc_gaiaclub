package org.soygaia.msvc.gaiaclub.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.productos.RecompensaProductoDTO;
import org.soygaia.msvc.gaiaclub.models.entity.VistaRecompense;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class VistaRecompensaRepository implements PanacheRepository<VistaRecompense> {
    @PersistenceContext
    private EntityManager entityManager;

    public List<RecompensaProductoDTO> findByPeriodo(Long periodoId) {

        return find("rec_periodo", periodoId).stream()
                .map(this::toRecompensaProductoDTO).collect(Collectors.toList());
    }

    public RecompensaProductoDTO findByIdRec(Long recompensaId) {
        return  toRecompensaProductoDTO(findById(recompensaId));
    }

    public Long findBySkuProdAndPeriodo(String sku, Long periodoID) {
        return Optional.ofNullable(find("prd_sku=?1 AND rec_periodo=?2", sku, periodoID).firstResult())
                .map(result -> ((VistaRecompense) result).getRec_id())
                .orElse(null);
    }

    public List<RecompensaProductoDTO> findAllDTOs(int pagina, int tamanoPagina){
        List<VistaRecompense> lista = findAll()
                .page(Page.of(pagina, tamanoPagina))
                .list();

        return lista.stream().map(this::toRecompensaProductoDTO).collect(Collectors.toList());
    }

    private RecompensaProductoDTO toRecompensaProductoDTO(VistaRecompense v){
        return new RecompensaProductoDTO(
                v.getRec_id(),
                v.getRec_aportesoles(),
                v.getRec_descripcion(),
                v.getRec_producto(),
                v.getPvt_preciomaximo(),
                v.getRec_nombre(),
                v.getRec_puntosreq(),
                v.getRec_stock(),
                v.getRec_periodo(),
                v.getPrd_nombre(),
                v.getPrd_slug(),
                v.getUmd_pres_abreviatura(),
                v.getUmd_abreviatura(),
                v.getPpd_nombre(),
                v.getPrd_sku(),
                v.getMrc_nombre(),
                v.getPrd_cantidadmedida(),
                v.getImagen_urls()
        );
    }

    public List<RecompensaProductoDTO> findBySkuOrNombre(String termino) {
        return entityManager.createQuery("SELECT r FROM VistaRecompense r WHERE prd_sku=:termino OR LOWER(r.prd_nombre) LIKE :termino OR LOWER(r.rec_nombre) LIKE :termino", VistaRecompense.class)
                .setParameter("termino", '%'+termino+'%')
                .getResultList().stream().map(this::toRecompensaProductoDTO).toList();
    }

    public List<RecompensaProductoDTO> findAllMenosPeriodo(Long periodoId) {
        return entityManager.createQuery("SELECT r FROM VistaRecompense r WHERE rec_periodo!=:periodoId", VistaRecompense.class)
                .setParameter("periodoId", periodoId)
                .getResultList().stream().map(this::toRecompensaProductoDTO).toList();
    }
}
