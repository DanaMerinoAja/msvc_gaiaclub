package org.soygaia.msvc.gaiaclub.models.dtos.recompensas.vales;

public class RecompensaValeDTO {
    private Long recId;
    private double descuentoSoles;
    private String nombreRecompensa;
    private String descripcionRecompensa;
    private int vigencia;
    private int puntosRequeridos;

    public RecompensaValeDTO(Long recId, double descuentoSoles, String nombreRecompensa, String descripcionRecompensa, int vigencia, int puntosRequeridos) {
        this.recId = recId;
        this.nombreRecompensa = nombreRecompensa;
        this.descripcionRecompensa = descripcionRecompensa;
        this.vigencia = vigencia;
        this.puntosRequeridos = puntosRequeridos;
        this.descuentoSoles = descuentoSoles;
    }



    public int getVigencia() {
        return vigencia;
    }

    public void setVigencia(int vigencia) {
        this.vigencia = vigencia;
    }

    public Long getRecId() {
        return recId;
    }

    public void setRecId(Long recId) {
        this.recId = recId;
    }

    public double getDescuentoSoles() {
        return descuentoSoles;
    }

    public void setDescuentoSoles(double descuentoSoles) {
        this.descuentoSoles = descuentoSoles;
    }

    public String getNombreRecompensa() {
        return nombreRecompensa;
    }

    public void setNombreRecompensa(String nombreRecompensa) {
        this.nombreRecompensa = nombreRecompensa;
    }

    public String getDescripcionRecompensa() {
        return descripcionRecompensa;
    }

    public void setDescripcionRecompensa(String descripcionRecompensa) {
        this.descripcionRecompensa = descripcionRecompensa;
    }

    public int getPuntosRequeridos() {
        return puntosRequeridos;
    }

    public void setPuntosRequeridos(int puntosRequeridos) {
        this.puntosRequeridos = puntosRequeridos;
    }
}
