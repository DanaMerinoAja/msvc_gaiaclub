package org.soygaia.msvc.gaiaclub.models.dtos;

import java.util.List;

public class CanjeRequestDTO {
    private int totalPuntos;
    private Long miembroId;
    private Long periodo;
    private List<DetalleCanje> detallesCanje;

    public Long getMiembroId() {
        return miembroId;
    }

    public void setMiembroId(Long miembroId) {
        this.miembroId = miembroId;
    }

    public List<DetalleCanje> getCanjeRequestDTOS() {
        return detallesCanje;
    }

    public Long getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Long periodo) {
        this.periodo = periodo;
    }

    public List<DetalleCanje> getDetallesCanje() {
        return detallesCanje;
    }

    public void setDetallesCanje(List<DetalleCanje> detallesCanje) {
        this.detallesCanje = detallesCanje;
    }

    public void setCanjeRequestDTOS(List<DetalleCanje> canjeRequestDTOS) {
        this.detallesCanje = canjeRequestDTOS;
    }

    public int getTotalPuntos() {
        return totalPuntos;
    }

    public void setTotalPuntos(int totalPuntos) {
        this.totalPuntos = totalPuntos;
    }
}
