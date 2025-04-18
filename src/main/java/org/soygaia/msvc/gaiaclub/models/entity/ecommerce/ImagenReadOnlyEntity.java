package org.soygaia.msvc.gaiaclub.models.entity.ecommerce;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "t_imagenproducto")
@Immutable
public class ImagenReadOnlyEntity {
    @Id
    @Column(name = "img_id")
    private Long id;

    @Column(name = "img_nombre")
    private String imgNombre;
    @Column(name = "img_url")
    private String imgUrl;
    @Column(name = "img_estado")
    private String imgEstado;

    @JoinColumn(name = "img_producto", referencedColumnName = "prd_id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private ProductoReadOnlyEntity producto;

    @JoinColumn(name = "img_precioventa", referencedColumnName = "pvt_id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private PrecioVentaReadOnlyEntity imgPrecioventa;

    public Long getId() {
        return id;
    }

    public PrecioVentaReadOnlyEntity getImgPrecioventa() {
        return imgPrecioventa;
    }

    public String getImgNombre() {
        return imgNombre;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getImgEstado() {
        return imgEstado;
    }

    public ProductoReadOnlyEntity getProducto() {
        return producto;
    }
}
