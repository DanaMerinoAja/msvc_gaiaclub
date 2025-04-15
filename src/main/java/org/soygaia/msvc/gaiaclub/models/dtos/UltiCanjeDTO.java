package org.soygaia.msvc.gaiaclub.models.dtos;

public class UltiCanjeDTO {
    private String recompensaNombre;
    private int puntosUsados;

    public UltiCanjeDTO(String recompensaNombre, int puntosUsados) {
        this.recompensaNombre = recompensaNombre;
        this.puntosUsados = puntosUsados;
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
}
