package org.soygaia.msvc.gaiaclub.models.dtos;

import jakarta.validation.constraints.NotNull;

public class CanjeRequestDTO {
    @NotNull(message = "Debe seleccionar una recompensa")
    private Long recompensaId;
    @NotNull(message = "El ID del cliente es obligatorio")
    private Long clienteId;
    private int cantidadRecompensa;

    public int getCantidadRecompensa() {
        return cantidadRecompensa;
    }

    public void setCantidadRecompensa(int cantidadRecompensa) {
        this.cantidadRecompensa = cantidadRecompensa;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getRecompensaId() {
        return recompensaId;
    }

    public void setRecompensaId(Long recompensaId) {
        this.recompensaId = recompensaId;
    }
}
