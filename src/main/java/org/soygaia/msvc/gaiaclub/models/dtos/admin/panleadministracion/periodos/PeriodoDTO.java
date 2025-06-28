package org.soygaia.msvc.gaiaclub.models.dtos.admin.panleadministracion.periodos;

import java.time.LocalDate;

public class PeriodoDTO {

    private LocalDate fechaFin;
    private LocalDate fechaInicio;
    private String descripcion;
    private String nombre;
    private Long idPeriodo;

    public PeriodoDTO(LocalDate fechaFin, LocalDate fechaInicio, String descripcion, String nombre, Long idPeriodo) {
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.idPeriodo = idPeriodo;
    }

    public PeriodoDTO() {
    }

    public Long getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Long idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
}
