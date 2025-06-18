package org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.canjes;

import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.productos.RecompensaProductoDTO;

public class UltimosCanjesDTO {
    private int puntosDetCanje;
    private RecompensaProductoDTO recompensaProductoDTO;
    private int cantidadRecompensa;

    public UltimosCanjesDTO() {
    }

    public int getPuntosDetCanje() {
        return puntosDetCanje;
    }

    public void setPuntosDetCanje(int puntosDetCanje) {
        this.puntosDetCanje = puntosDetCanje;
    }

    public RecompensaProductoDTO getRecompensaProductoDTO() {
        return recompensaProductoDTO;
    }

    public void setRecompensaProductoDTO(RecompensaProductoDTO recompensaProductoDTO) {
        this.recompensaProductoDTO = recompensaProductoDTO;
    }

    public int getCantidadRecompensa() {
        return cantidadRecompensa;
    }

    public void setCantidadRecompensa(int cantidadRecompensa) {
        this.cantidadRecompensa = cantidadRecompensa;
    }
}
