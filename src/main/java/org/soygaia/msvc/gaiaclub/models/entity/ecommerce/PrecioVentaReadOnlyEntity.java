package org.soygaia.msvc.gaiaclub.models.entity.ecommerce;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "t_precioventa")
@Immutable
public class PrecioVentaReadOnlyEntity {
    @Id
    @Column(name = "pvt_id")
    private Long pvtId;
    @Column(name = "pvt_preciomaximo")
    private BigDecimal pvtPreciomaximo;
    @Column(name = "pvt_preciocosto")
    private BigDecimal pvtPreciocosto;
    @JoinColumn(name = "pvt_producto", referencedColumnName = "prd_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ProductoReadOnlyEntity pvtProducto;
    @JoinColumn(name = "pvt_unidadmedida", referencedColumnName = "umd_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UnidadMedidaReadOnlyEntity pvtUnidadmedida;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "imgPrecioventa", fetch = FetchType.LAZY)
    private List<ImagenReadOnlyEntity> lstImagenproducto;


    public Long getPvtId() {
        return pvtId;
    }

    public BigDecimal getPvtPreciomaximo() {
        return pvtPreciomaximo;
    }

    public BigDecimal getPvtPreciocosto() {
        return pvtPreciocosto;
    }

    public ProductoReadOnlyEntity getPvtProducto() {
        return pvtProducto;
    }

    public UnidadMedidaReadOnlyEntity getPvtUnidadmedida() {
        return pvtUnidadmedida;
    }

    public List<ImagenReadOnlyEntity> getLstImagenproducto() {
        return lstImagenproducto;
    }
}
