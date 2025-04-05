package org.soygaia.msvc.gaiaclub.models.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public class PuntosRegistroDTO {

    @NotNull(message = "El ID del cliente es obligatorio")
    private Long idCliente;

    @NotNull(message = "El total de puntos es obligatorio")
    @Min(value = 1, message = "Debe registrar al menos 1 punto")
    private Integer totalPuntos;

    @NotNull(message = "El ID de origen es obligatorio")
    private Long idOrigen;

    @NotBlank(message = "El tipo de origen es obligatorio")
    @Pattern(regexp = "COMPRA|BONIFICACION")
    private String tipoOrigen;

    @NotNull(message = "La fecha de emisión es obligatoria")
    private LocalDate fechaEmision;



    public int getTotalPuntos() {
        return totalPuntos;
    }

    public void setTotalPuntos(int totalPuntos) {
        this.totalPuntos = totalPuntos;
    }

    public Long getIdOrigen() {
        return idOrigen;
    }

    public void setIdOrigen(Long idOrigen) {
        this.idOrigen = idOrigen;
    }

    public String getTipoOrigen() {
        return tipoOrigen;
    }

    public void setTipoOrigen(String tipoOrigen) {
        this.tipoOrigen = tipoOrigen;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
}
