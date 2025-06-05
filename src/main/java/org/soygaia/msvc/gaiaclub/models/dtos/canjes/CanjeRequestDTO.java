package org.soygaia.msvc.gaiaclub.models.dtos.canjes;

import java.util.List;

public class CanjeRequestDTO {
    private int totalPuntos;
    private Long miembroId;
    private Long periodoId;
    private List<DetalleCanjePost> detallesCanje;

    public Long getMiembroId() {
        return miembroId;
    }

    public void setMiembroId(Long miembroId) {
        this.miembroId = miembroId;
    }

    public List<DetalleCanjePost> getCanjeRequestDTOS() {
        return detallesCanje;
    }

    public Long getPeriodoId() {
        return periodoId;
    }

    public void setPeriodoId(Long periodoId) {
        this.periodoId = periodoId;
    }

    public List<DetalleCanjePost> getDetallesCanje() {
        return detallesCanje;
    }

    public void setDetallesCanje(List<DetalleCanjePost> detallesCanje) {
        this.detallesCanje = detallesCanje;
    }

    public int getTotalPuntos() {
        return totalPuntos;
    }

    public void setTotalPuntos(int totalPuntos) {
        this.totalPuntos = totalPuntos;
    }
}
