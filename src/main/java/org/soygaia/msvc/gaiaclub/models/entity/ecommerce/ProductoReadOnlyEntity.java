package org.soygaia.msvc.gaiaclub.models.entity.ecommerce;
import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

import java.util.List;

@Entity
@Table(name = "t_producto", schema = "gaiaadm")
@Immutable // Solo lectura ya que no cambiamos nada del proyecto original
public class ProductoReadOnlyEntity {

    @Id
    @Column(name = "prd_id")
    private Long id;

    @Column(name = "prd_nombre")
    private String nombre;

    @Column(name = "prd_slug")
    private String slug;

    @Column(name = "prd_sku")
    private String sku;

    @Column(name = "prd_cantidadmedida")
    private String cantidadMedida;

    @Column(name = "prd_descripcioncorta")
    private String descripcionCorta;

    @Column(name = "prd_descripcion")
    private String descripcionLarga;

    @JoinColumn(name = "prd_marca", referencedColumnName = "mrc_id")
    @ManyToOne
    private MarcaReadOnlyEntity prdMarca;

    // Getters (no necesitas setters)
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getSlug() { return slug; }
    public String getSku() { return sku; }
    public String getCantidadMedida() { return cantidadMedida; }
    public String getDescripcionCorta() { return descripcionCorta; }
    public String getDescripcionLarga() { return descripcionLarga; }

    public MarcaReadOnlyEntity getPrdMarca() {
        return prdMarca;
    }
}
