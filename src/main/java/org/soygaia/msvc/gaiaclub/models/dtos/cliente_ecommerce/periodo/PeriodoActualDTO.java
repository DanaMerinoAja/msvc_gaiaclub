package org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.periodo;

import java.time.LocalDate;

public class PeriodoActualDTO {

    private LocalDate fechaFin;
    private LocalDate fechaInicio;
    private String descripcion;
    private String nombre;
    private Long idPeriodo;
    private double valorPunto;

    public double getValorPunto() {
        return valorPunto;
    }

    public void setValorPunto(double valorPunto) {
        this.valorPunto = valorPunto;
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
