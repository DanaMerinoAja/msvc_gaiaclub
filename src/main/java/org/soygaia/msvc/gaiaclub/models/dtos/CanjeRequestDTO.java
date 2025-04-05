package org.soygaia.msvc.gaiaclub.models.dtos;

import jakarta.validation.constraints.NotNull;

public class CanjeRequestDTO {
    @NotNull(message = "El ID del cliente es obligatorio")
    private Long idCliente;

    @NotNull(message = "Debe seleccionar una recompensa")
    private Long idRecompensa;

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdRecompensa() {
        return idRecompensa;
    }

    public void setIdRecompensa(Long idRecompensa) {
        this.idRecompensa = idRecompensa;
    }
}
