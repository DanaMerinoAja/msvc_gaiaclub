package org.soygaia.msvc.gaiaclub.models.dtos.admin.bonificaciones;

import java.time.LocalDateTime;

public class BonificacionDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private Integer puntos;
    private boolean activa;
    private LocalDateTime fechaCreacion;

    public BonificacionDTO() {
    }

    public BonificacionDTO(Long id, String nombre, String descripcion, Integer puntos, boolean activa, LocalDateTime fechaCreacion) {
        this.id = id;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.puntos = puntos;
        this.activa = activa;
        this.fechaCreacion = fechaCreacion;
    }

    public BonificacionDTO(String nombre,String descripcion, Integer puntos) {
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.puntos = puntos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
