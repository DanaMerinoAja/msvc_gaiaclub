package org.soygaia.msvc.gaiaclub.models.dtos;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class PeriodoDTO {
    @NotNull(message = "La fecha de inicio es obligatoria")
    @FutureOrPresent(message = "La fecha de inicio debe ser hoy o en el futuro")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin es obligatoria")
    @Future(message = "La fecha de fin debe ser en el futuro")
    private LocalDate fechaFin;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 255, message = "La descripción no puede exceder los 255 caracteres")
    private String descripcion;

    @NotBlank(message = "El nombre del periodo es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
    private String nombre;

    @NotNull(message = "El valor del punto es obligatorio")
    @Min(value = 0, message = "El valor del punto no puede ser negativo")
    private double valorPunto;

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
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

    public double getValorPunto() {
        return valorPunto;
    }

    public void setValorPunto(double valorPunto) {
        this.valorPunto = valorPunto;
    }
}
