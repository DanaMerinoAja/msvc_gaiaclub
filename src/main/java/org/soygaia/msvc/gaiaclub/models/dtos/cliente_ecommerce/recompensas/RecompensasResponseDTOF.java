package org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas;

import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.productos.RecompensaProductoDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.vales.RecompensaValeDTO;

import java.util.List;

public class RecompensasResponseDTOF {
    private boolean periodo;
    private List<RecompensaProductoDTO> recompensasProducto;
    private List<RecompensaValeDTO> recompensasVale;

    // Constructor
    public RecompensasResponseDTOF(List<RecompensaProductoDTO> recompensasProducto, List<RecompensaValeDTO> recompensasVale, boolean periodo) {
        this.recompensasProducto = recompensasProducto;
        this.recompensasVale = recompensasVale;
        this.periodo = periodo;
    }

    public boolean isPeriodo() {
        return periodo;
    }

    public void setPeriodo(boolean periodo) {
        this.periodo = periodo;
    }

    public List<RecompensaProductoDTO> getRecompensasProducto() {
        return recompensasProducto;
    }

    public void setRecompensasProducto(List<RecompensaProductoDTO> recompensasProducto) {
        this.recompensasProducto = recompensasProducto;
    }

    public List<RecompensaValeDTO> getRecompensasVale() {
        return recompensasVale;
    }

    public void setRecompensasVale(List<RecompensaValeDTO> recompensasVale) {
        this.recompensasVale = recompensasVale;
    }
}
