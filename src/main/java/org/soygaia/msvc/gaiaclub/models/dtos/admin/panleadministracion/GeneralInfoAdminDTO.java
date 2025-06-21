package org.soygaia.msvc.gaiaclub.models.dtos.admin.panleadministracion;

public class GeneralInfoAdminDTO {
    private int puntosBienvenida;
    private int puntosPorCompra;
    private double valorCompra;
    private int puntosVigenciaMeses;
    private double valorPuntos;

    public int getPuntosBienvenida() {
        return puntosBienvenida;
    }

    public void setPuntosBienvenida(int puntosBienvenida) {
        this.puntosBienvenida = puntosBienvenida;
    }

    public int getPuntosPorCompra() {
        return puntosPorCompra;
    }

    public void setPuntosPorCompra(int puntosPorCompra) {
        this.puntosPorCompra = puntosPorCompra;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public int getPuntosVigenciaMeses() {
        return puntosVigenciaMeses;
    }

    public void setPuntosVigenciaMeses(int puntosVigenciaMeses) {
        this.puntosVigenciaMeses = puntosVigenciaMeses;
    }

    public double getValorPuntos() {
        return valorPuntos;
    }

    public void setValorPuntos(double valorPuntos) {
        this.valorPuntos = valorPuntos;
    }
}
