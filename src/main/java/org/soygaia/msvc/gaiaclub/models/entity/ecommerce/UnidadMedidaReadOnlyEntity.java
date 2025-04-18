package org.soygaia.msvc.gaiaclub.models.entity.ecommerce;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

import java.util.List;

@Entity
@Table(name = "t_unidadmedida", schema = "gaiaadm")
@Immutable // Solo lectura ya que no cambiamos nada del proyecto original
public class UnidadMedidaReadOnlyEntity {
    @Id
    @Column(name = "umd_id")
    private Long umdId;
    @Column(name = "umd_nombre")
    private String umdNombre;
    @Column(name = "umd_abreviatura")
    private String umdAbreviatura;
    @Column(name = "unm_cantidad")
    private Integer unmCantidad;
    @Column(name = "umd_presentacion")
    private Boolean umdPresentacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pvtUnidadmedida", fetch = FetchType.LAZY)
    private List<PrecioVentaReadOnlyEntity> precioventaEntityList;

    public Long getUmdId() {
        return umdId;
    }

    public void setUmdId(Long umdId) {
        this.umdId = umdId;
    }

    public String getUmdNombre() {
        return umdNombre;
    }

    public void setUmdNombre(String umdNombre) {
        this.umdNombre = umdNombre;
    }

    public String getUmdAbreviatura() {
        return umdAbreviatura;
    }

    public void setUmdAbreviatura(String umdAbreviatura) {
        this.umdAbreviatura = umdAbreviatura;
    }

    public Integer getUnmCantidad() {
        return unmCantidad;
    }

    public void setUnmCantidad(Integer unmCantidad) {
        this.unmCantidad = unmCantidad;
    }

    public Boolean getUmdPresentacion() {
        return umdPresentacion;
    }

    public void setUmdPresentacion(Boolean umdPresentacion) {
        this.umdPresentacion = umdPresentacion;
    }

    public List<PrecioVentaReadOnlyEntity> getPrecioventaEntityList() {
        return precioventaEntityList;
    }

    public void setPrecioventaEntityList(List<PrecioVentaReadOnlyEntity> precioventaEntityList) {
        this.precioventaEntityList = precioventaEntityList;
    }
}
