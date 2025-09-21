package org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.canjes;

public class DetalleCanjePost {
    private Long recompensaId;
    private int cantidadRecompensa;

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