package org.soygaia.msvc.gaiaclub.models.dtos.admin.productos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Producto {
    private Long prdId;
    private String prdNombre;
    private String prdSlug;
    private String prdSku;

    private Marca prdMarca;
    private Presentacion prdPresentacion;

    private UnidadMedida prdUnidadmedida;        // unidad de venta (UND, etc.)
    private UnidadMedida prdUndmedpresentacion;  // unidad de presentación (GR, etc.)

    private String prdCantidadmedida;            // "250", "70", etc.
    private java.util.List<ImagenProducto> lstImagenproducto;

    // getters y setters
    public Long getPrdId() { return prdId; }
    public void setPrdId(Long prdId) { this.prdId = prdId; }
    public String getPrdNombre() { return prdNombre; }
    public void setPrdNombre(String prdNombre) { this.prdNombre = prdNombre; }
    public String getPrdSlug() { return prdSlug; }
    public void setPrdSlug(String prdSlug) { this.prdSlug = prdSlug; }
    public String getPrdSku() { return prdSku; }
    public void setPrdSku(String prdSku) { this.prdSku = prdSku; }
    public Marca getPrdMarca() { return prdMarca; }
    public void setPrdMarca(Marca prdMarca) { this.prdMarca = prdMarca; }
    public Presentacion getPrdPresentacion() { return prdPresentacion; }
    public void setPrdPresentacion(Presentacion prdPresentacion) { this.prdPresentacion = prdPresentacion; }
    public UnidadMedida getPrdUnidadmedida() { return prdUnidadmedida; }
    public void setPrdUnidadmedida(UnidadMedida prdUnidadmedida) { this.prdUnidadmedida = prdUnidadmedida; }
    public UnidadMedida getPrdUndmedpresentacion() { return prdUndmedpresentacion; }
    public void setPrdUndmedpresentacion(UnidadMedida prdUndmedpresentacion) { this.prdUndmedpresentacion = prdUndmedpresentacion; }
    public String getPrdCantidadmedida() { return prdCantidadmedida; }
    public void setPrdCantidadmedida(String prdCantidadmedida) { this.prdCantidadmedida = prdCantidadmedida; }
    public java.util.List<ImagenProducto> getLstImagenproducto() { return lstImagenproducto; }
    public void setLstImagenproducto(java.util.List<ImagenProducto> lstImagenproducto) { this.lstImagenproducto = lstImagenproducto; }
}