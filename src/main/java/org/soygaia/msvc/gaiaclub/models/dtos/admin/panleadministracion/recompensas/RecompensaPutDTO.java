package org.soygaia.msvc.gaiaclub.models.dtos.admin.panleadministracion.recompensas;

public class RecompensaPutDTO {
    private Long idRecompensa;
    private String nombre;
    private String descripcion;
    private Double aporteSoles;
    private Integer puntosRequeridos;
    private Integer stock;

    public Long getIdRecompensa() {
        return idRecompensa;
    }

    public void setIdRecompensa(Long idRecompensa) {
        this.idRecompensa = idRecompensa;
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
