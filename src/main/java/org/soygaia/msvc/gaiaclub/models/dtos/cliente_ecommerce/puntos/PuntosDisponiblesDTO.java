package org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.puntos;

import org.soygaia.msvc.gaiaclub.models.entity.PuntosEntity;

import java.util.List;

public class PuntosDisponiblesDTO {
    private List<PuntosEntity> puntos;
    private int totalDisponible;

    public PuntosDisponiblesDTO() {
    }

    public PuntosDisponiblesDTO(List<PuntosEntity> puntos, int totalDisponible) {
        this.puntos = puntos;
        this.totalDisponible = totalDisponible;
    }

    public List<PuntosEntity> getPuntos() {
        return puntos;
    }

    public void setPuntos(List<PuntosEntity> puntos) {
        this.puntos = puntos;
    }

    public int getTotalDisponible() {
        return totalDisponible;
    }

    public void setTotalDisponible(int totalDisponible) {
        this.totalDisponible = totalDisponible;
    }
}
