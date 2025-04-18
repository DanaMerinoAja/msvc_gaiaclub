package org.soygaia.msvc.gaiaclub.models.dtos;

import org.soygaia.msvc.gaiaclub.models.entity.ValeEntity;

public class RecompensaValeDTO {
    private Long recId;
    private double descuentoSoles;
    private String nombreRecompensa;
    private String descripcionRecompensa;
    private int vigencia;
    private int puntosRequeridos;
    private int stock;

    public RecompensaValeDTO(Long recId, double descuentoSoles, String nombreRecompensa, String descripcionRecompensa, int vigencia, int puntosRequeridos, int stock) {
        this.recId = recId;
        this.descuentoSoles = descuentoSoles;
        this.nombreRecompensa = nombreRecompensa;
        this.descripcionRecompensa = descripcionRecompensa;
        this.vigencia = vigencia;
        this.puntosRequeridos = puntosRequeridos;
        this.stock = stock;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
