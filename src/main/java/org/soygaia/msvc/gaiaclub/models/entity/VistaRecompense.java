package org.soygaia.msvc.gaiaclub.models.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import java.math.BigDecimal;

@Entity
@Immutable
@NamedNativeQuery(
        name = "VistaRecompense.findByPeriodo",
        query = "SELECT * FROM gaiaadm.v_recompensas_productos_periodo WHERE rec_periodo = :periodoId",
        resultClass = VistaRecompense.class
)
@Subselect("SELECT * FROM gaiaadm.v_recompensas_productos_periodo")
public class VistaRecompense {
    @Id
    private Long rec_id;
    @Column
    private Double rec_aportesoles;
    @Column
    private String rec_descripcion;
    @Column
    private Long rec_producto;
    @Column
    private BigDecimal pvt_preciomaximo;
    @Column
    private String rec_nombre;
    @Column
    private Integer rec_puntosreq;
    @Column
    private Integer rec_stock;
    @Column
    private Long rec_periodo;
    @Column
    private String prd_nombre;
    @Column
    private String prd_slug;
    @Column
    private String umd_pres_abreviatura;
    @Column
    private String umd_abreviatura;
    @Column
    private String ppd_nombre;
    @Column
    private String prd_sku;
    @Column
    private String mrc_nombre;
    @Column
    private String prd_cantidadmedida;
    @Column
    private String imagen_urls;

    public Long getRec_id() {
        return rec_id;
    }

    public void setRec_id(Long rec_id) {
        this.rec_id = rec_id;
    }

    public Double getRec_aportesoles() {
        return rec_aportesoles;
    }

    public void setRec_aportesoles(Double rec_aportesoles) {
        this.rec_aportesoles = rec_aportesoles;
    }

    public String getRec_descripcion() {
        return rec_descripcion;
    }

    public void setRec_descripcion(String rec_descripcion) {
        this.rec_descripcion = rec_descripcion;
    }

    public Long getRec_producto() {
        return rec_producto;
    }

    public void setRec_producto(Long rec_producto) {
        this.rec_producto = rec_producto;
    }

    public BigDecimal getPvt_preciomaximo() {
        return pvt_preciomaximo;
    }

    public void setPvt_preciomaximo(BigDecimal pvt_preciomaximo) {
        this.pvt_preciomaximo = pvt_preciomaximo;
    }

    public String getRec_nombre() {
        return rec_nombre;
    }

    public void setRec_nombre(String rec_nombre) {
        this.rec_nombre = rec_nombre;
    }

    public Integer getRec_puntosreq() {
        return rec_puntosreq;
    }

    public void setRec_puntosreq(Integer rec_puntosreq) {
        this.rec_puntosreq = rec_puntosreq;
    }

    public Integer getRec_stock() {
        return rec_stock;
    }

    public void setRec_stock(Integer rec_stock) {
        this.rec_stock = rec_stock;
    }

    public Long getRec_periodo() {
        return rec_periodo;
    }

    public void setRec_periodo(Long rec_periodo) {
        this.rec_periodo = rec_periodo;
    }

    public String getPrd_nombre() {
        return prd_nombre;
    }

    public void setPrd_nombre(String prd_nombre) {
        this.prd_nombre = prd_nombre;
    }

    public String getPrd_slug() {
        return prd_slug;
    }

    public void setPrd_slug(String prd_slug) {
        this.prd_slug = prd_slug;
    }

    public String getUmd_pres_abreviatura() {
        return umd_pres_abreviatura;
    }

    public void setUmd_pres_abreviatura(String umd_pres_abreviatura) {
        this.umd_pres_abreviatura = umd_pres_abreviatura;
    }

    public String getUmd_abreviatura() {
        return umd_abreviatura;
    }

    public void setUmd_abreviatura(String umd_abreviatura) {
        this.umd_abreviatura = umd_abreviatura;
    }

    public String getPpd_nombre() {
        return ppd_nombre;
    }

    public void setPpd_nombre(String ppd_nombre) {
        this.ppd_nombre = ppd_nombre;
    }

    public String getPrd_sku() {
        return prd_sku;
    }

    public void setPrd_sku(String prd_sku) {
        this.prd_sku = prd_sku;
    }

    public String getMrc_nombre() {
        return mrc_nombre;
    }

    public void setMrc_nombre(String mrc_nombre) {
        this.mrc_nombre = mrc_nombre;
    }

    public String getPrd_cantidadmedida() {
        return prd_cantidadmedida;
    }

    public void setPrd_cantidadmedida(String prd_cantidadmedida) {
        this.prd_cantidadmedida = prd_cantidadmedida;
    }

    public String getImagen_urls() {
        return imagen_urls;
    }

    public void setImagen_urls(String imagen_urls) {
        this.imagen_urls = imagen_urls;
    }
}

