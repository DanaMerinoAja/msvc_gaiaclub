package org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.productos;

import java.math.BigDecimal;

public class RecompensaProductoDTO {

    private Long recId;
    private Double aporteSoles;
    private String descripcion;
    private Long recProducto;
    private BigDecimal precioProducto;
    private String nombreRecompensa;
    private int puntosRequeridos;
    private int stock;
    private Long periodoId;
    private String nombreProducto;
    private String slugProducto;
    private String abreviaturaUnidadMedidaPres;
    private String unidadMedidaVenta;
    private String presentacion;
    private String sku;
    private String marca;
    private String [] imagenUrl;
    private String cantidadMedida;

    // Constructor
    public String getCantidadMedida() {
        return cantidadMedida;
    }

    public void setCantidadMedida(String cantidadMedida) {
        this.cantidadMedida = cantidadMedida;
    }


    public RecompensaProductoDTO(Long recId, Double aporteSoles, String descripcion, Long recProducto, BigDecimal precioProducto,
                                 String nombreRecompensa, int puntosRequeridos, int stock, Long periodoId, String nombreProducto,
                                 String slugProducto, String abreviaturaUnidadMedidaPres, String nombreUnidadMedida, String presentacion,
                                 String sku, String marca, String cantidadMedida, String imagenUrls) {
        this.recId = recId;
        this.aporteSoles = aporteSoles;
        this.descripcion = descripcion;
        this.recProducto = recProducto;
        this.precioProducto = precioProducto;
        this.nombreRecompensa = nombreRecompensa;
        this.puntosRequeridos = puntosRequeridos;
        this.stock = stock;
        this.periodoId = periodoId;
        this.nombreProducto = nombreProducto;
        this.slugProducto = slugProducto;
        this.abreviaturaUnidadMedidaPres = abreviaturaUnidadMedidaPres;
        this.unidadMedidaVenta = nombreUnidadMedida;
        this.presentacion = presentacion;
        this.sku = sku;
        this.cantidadMedida =cantidadMedida;
        this.marca = marca;
        if (imagenUrls != null && !imagenUrls.isEmpty()) {
            this.imagenUrl = imagenUrls.split(",");
        }
    }

    public BigDecimal getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(BigDecimal precioProducto) {
        this.precioProducto = precioProducto;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setImagenUrl(String[] imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public Long getRecId() { return recId; }
    public void setRecId(Long recId) { this.recId = recId; }

    public Double getAporteSoles() { return aporteSoles; }
    public void setAporteSoles(Double aporteSoles) { this.aporteSoles = aporteSoles; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Long getRecProducto() { return recProducto; }
    public void setRecProducto(Long recProducto) { this.recProducto = recProducto; }

    public String getNombreRecompensa() { return nombreRecompensa; }
    public void setNombreRecompensa(String nombreRecompensa) { this.nombreRecompensa = nombreRecompensa; }

    public int getPuntosRequeridos() { return puntosRequeridos; }
    public void setPuntosRequeridos(int puntosRequeridos) { this.puntosRequeridos = puntosRequeridos; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public Long getPeriodoId() { return periodoId; }
    public void setPeriodoId(Long periodoId) { this.periodoId = periodoId; }

    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }

    public String getSlugProducto() { return slugProducto; }
    public void setSlugProducto(String slugProducto) { this.slugProducto = slugProducto; }

    public String getAbreviaturaUnidadMedidaPres() { return abreviaturaUnidadMedidaPres; }
    public void setAbreviaturaUnidadMedidaPres(String abreviaturaUnidadMedidaPres) { this.abreviaturaUnidadMedidaPres = abreviaturaUnidadMedidaPres; }

    public String getUnidadMedidaVenta() { return unidadMedidaVenta; }
    public void setUnidadMedidaVenta(String unidadMedidaVenta) { this.unidadMedidaVenta = unidadMedidaVenta; }

    public String[] getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl.split(","); }
}
