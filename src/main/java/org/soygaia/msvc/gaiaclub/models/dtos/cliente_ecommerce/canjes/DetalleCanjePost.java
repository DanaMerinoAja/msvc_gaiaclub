package org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.canjes;

public class DetalleCanjePost {
    private int puntosDetCanje;
    private Long recompensaId;
    private int cantidadRecompensa;

    public int getPuntosDetCanje() {
        return puntosDetCanje;
    }

    public void setPuntosDetCanje(int puntosDetCanje) {
        this.puntosDetCanje = puntosDetCanje;
    }

    public Long getRecompensaId() {
        return recompensaId;
    }

    public void setRecompensaId(Long recompensaId) {
        this.recompensaId = recompensaId;
    }

    public int getCantidadRecompensa() {
        return cantidadRecompensa;
    }

    public void setCantidadRecompensa(int cantidadRecompensa) {
        this.cantidadRecompensa = cantidadRecompensa;
    }
}
