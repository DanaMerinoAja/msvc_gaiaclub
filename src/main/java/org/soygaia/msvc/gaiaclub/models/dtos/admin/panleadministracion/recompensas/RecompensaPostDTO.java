package org.soygaia.msvc.gaiaclub.models.dtos.admin.panleadministracion.recompensas;

public class RecompensaPostDTO {

    private Long idProducto;
    private Long idPeriodo;
    private Double aporteSoles;
    private Integer puntosRequeridos;
    private Integer stock;
    private String descripcion;
    private String nombre;

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Long getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Long idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getAporteSoles() {
        return aporteSoles;
    }

    public void setAporteSoles(Double aporteSoles) {
        this.aporteSoles = aporteSoles;
    }

    public Integer getPuntosRequeridos() {
        return puntosRequeridos;
    }

    public void setPuntosRequeridos(Integer puntosRequeridos) {
        this.puntosRequeridos = puntosRequeridos;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
