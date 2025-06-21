package org.soygaia.msvc.gaiaclub.models.dtos.admin.panelcanjes;

public class DetalleRecompensaCanjeDTO {
    private String imagen;
    private String sku;
    private String nombre;
    private Integer puntos;
    private Double aporteSoles;
    private Double valorOriginal;
    private Double porcentajeDescuento;
    private Integer cantidad;

    public DetalleRecompensaCanjeDTO(String imagen, String sku, String nombre, Integer puntos, Double aporteSoles, Double valorOriginal, Double porcentajeDescuento, Integer cantidad) {
        this.imagen = imagen;
        this.sku = sku;
        this.nombre = nombre;
        this.puntos = puntos;
        this.aporteSoles = aporteSoles;
        this.valorOriginal = valorOriginal;
        this.porcentajeDescuento = porcentajeDescuento;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public Double getAporteSoles() {
        return aporteSoles;
    }

    public void setAporteSoles(Double aporteSoles) {
        this.aporteSoles = aporteSoles;
    }

    public Double getValorOriginal() {
        return valorOriginal;
    }

    public void setValorOriginal(Double valorOriginal) {
        this.valorOriginal = valorOriginal;
    }

    public Double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(Double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
