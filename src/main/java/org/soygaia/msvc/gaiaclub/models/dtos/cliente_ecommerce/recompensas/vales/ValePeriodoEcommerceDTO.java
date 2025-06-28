package org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.vales;

public class ValePeriodoEcommerceDTO {
    private long valeId;
    private double descuentoSoles;
    private String nombreVale;
    private String descripcionVale;
    private int vigencia;
    private int puntosRequeridos;
    private long periodoId;

    public ValePeriodoEcommerceDTO(long valeId, double descuentoSoles, String nombreVale, String descripcionVale, int vigencia, int puntosRequeridos, long periodoId) {
        this.valeId = valeId;
        this.descuentoSoles = descuentoSoles;
        this.nombreVale = nombreVale;
        this.descripcionVale = descripcionVale;
        this.vigencia = vigencia;
        this.puntosRequeridos = puntosRequeridos;
        this.periodoId = periodoId;
    }

    public long getValeId() {
        return valeId;
    }

    public void setValeId(long valeId) {
        this.valeId = valeId;
    }

    public double getDescuentoSoles() {
        return descuentoSoles;
    }

    public void setDescuentoSoles(double descuentoSoles) {
        this.descuentoSoles = descuentoSoles;
    }

    public String getNombreVale() {
        return nombreVale;
    }

    public void setNombreVale(String nombreVale) {
        this.nombreVale = nombreVale;
    }

    public String getDescripcionVale() {
        return descripcionVale;
    }

    public void setDescripcionVale(String descripcionVale) {
        this.descripcionVale = descripcionVale;
    }

    public int getVigencia() {
        return vigencia;
    }

    public void setVigencia(int vigencia) {
        this.vigencia = vigencia;
    }

    public int getPuntosRequeridos() {
        return puntosRequeridos;
    }

    public void setPuntosRequeridos(int puntosRequeridos) {
        this.puntosRequeridos = puntosRequeridos;
    }

    public long getPeriodoId() {
        return periodoId;
    }

    public void setPeriodoId(long periodoId) {
        this.periodoId = periodoId;
    }
}
