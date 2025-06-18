package org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.periodo;

public class PeriodoResponseDTO {
    private boolean isCurrent;
    private PeriodoActualDTO periodo;
    private double puntosCompra;
    private double valorCompra;
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }

    public PeriodoActualDTO getPeriodo() {
        return periodo;
    }

    public void setPeriodo(PeriodoActualDTO periodo) {
        this.periodo = periodo;
    }

    public double getPuntosCompra() {
        return puntosCompra;
    }

    public void setPuntosCompra(double puntosCompra) {
        this.puntosCompra = puntosCompra;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }
}
