package org.soygaia.msvc.gaiaclub.models.dtos;

import java.util.List;

public class CanjesRequestDTO {
    private List<CanjeRequestDTO> canjeRequestDTOS;
    private int totalPuntos;
    private Long clienteId;

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<CanjeRequestDTO> getCanjeRequestDTOS() {
        return canjeRequestDTOS;
    }

    public void setCanjeRequestDTOS(List<CanjeRequestDTO> canjeRequestDTOS) {
        this.canjeRequestDTOS = canjeRequestDTOS;
    }

    public int getTotalPuntos() {
        return totalPuntos;
    }

    public void setTotalPuntos(int totalPuntos) {
        this.totalPuntos = totalPuntos;
    }
}
