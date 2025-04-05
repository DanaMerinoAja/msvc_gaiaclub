package org.soygaia.msvc.gaiaclub.models.dtos;

public class RecompensaDTO {
    private Long idProducto;
    private Long idPeriodo;
    private String nombre;
    private double aporteSoles;
    private int puntosRequeridos;
    private int stock;
    private String descripcion;

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long id) {
        this.idProducto = id;
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

    public double getAporteSoles() {
        return aporteSoles;
    }

    public void setAporteSoles(double aporteSoles) {
        this.aporteSoles = aporteSoles;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPuntosRequeridos() {
        return puntosRequeridos;
    }

    public void setPuntosRequeridos(int puntosRequeridos) {
        this.puntosRequeridos = puntosRequeridos;
    }
}
