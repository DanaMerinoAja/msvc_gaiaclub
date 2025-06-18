package org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.canjes;

import java.time.LocalDate;

public class CanjeDTO {
    private String recompensaNombre;
    private int puntosUsados;
    private LocalDate fechaCanje;
    private int stockRestante;
    private String mensaje; // para UX

    public CanjeDTO(String recompensaNombre, int puntosUsados, LocalDate fechaCanje, int stockRestante, String mensaje) {
        this.recompensaNombre = recompensaNombre;
        this.puntosUsados = puntosUsados;
        this.fechaCanje = fechaCanje;
        this.stockRestante = stockRestante;
        this.mensaje = mensaje;
    }

    public CanjeDTO() {
    }

    public String getRecompensaNombre() {
        return recompensaNombre;
    }

    public void setRecompensaNombre(String recompensaNombre) {
        this.recompensaNombre = recompensaNombre;
    }

    public int getPuntosUsados() {
        return puntosUsados;
    }

    public void setPuntosUsados(int puntosUsados) {
        this.puntosUsados = puntosUsados;
    }

    public LocalDate getFechaCanje() {
        return fechaCanje;
    }

    public void setFechaCanje(LocalDate fechaCanje) {
        this.fechaCanje = fechaCanje;
    }

    public int getStockRestante() {
        return stockRestante;
    }

    public void setStockRestante(int stockRestante) {
        this.stockRestante = stockRestante;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
