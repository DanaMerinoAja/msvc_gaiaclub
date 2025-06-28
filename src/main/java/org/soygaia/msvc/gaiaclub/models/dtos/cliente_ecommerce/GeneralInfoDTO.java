package org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce;

public class GeneralInfoDTO {
    private double puntosPorCompra;
    private double valorCompra;
    private double valorPuntoSoles;

    public GeneralInfoDTO(double puntosPorCompra, double valorCompra, double valorPuntoSoles) {
        this.puntosPorCompra = puntosPorCompra;
        this.valorCompra = valorCompra;
        this.valorPuntoSoles = valorPuntoSoles;
    }

    public double getPuntosPorCompra() {
        return puntosPorCompra;
    }

    public void setPuntosPorCompra(double puntosPorCompra) {
        this.puntosPorCompra = puntosPorCompra;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public double getValorPuntoSoles() {
        return valorPuntoSoles;
    }

    public void setValorPuntoSoles(double valorPuntoSoles) {
        this.valorPuntoSoles = valorPuntoSoles;
    }
}
