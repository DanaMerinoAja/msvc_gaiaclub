package org.soygaia.msvc.gaiaclub.models.dtos.admin.panleadministracion.vales;

public class ValeBaseDTO {
    private Long valeBaseId;
    private double descuentoSoles;
    private String nombreVale;
    private String descripcionVale;
    private int vigencia;
    private int puntosRequeridos;

    public ValeBaseDTO(Long valeBaseId, double descuentoSoles, String nombreVale, String descripcionVale, int vigencia, int puntosRequeridos) {
        this.valeBaseId = valeBaseId;
        this.descuentoSoles = descuentoSoles;
        this.nombreVale = nombreVale;
        this.descripcionVale = descripcionVale;
        this.vigencia = vigencia;
        this.puntosRequeridos = puntosRequeridos;
    }

    public Long getValeBaseId() {
        return valeBaseId;
    }

    public void setValeBaseId(Long valeBaseId) {
        this.valeBaseId = valeBaseId;
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
}
