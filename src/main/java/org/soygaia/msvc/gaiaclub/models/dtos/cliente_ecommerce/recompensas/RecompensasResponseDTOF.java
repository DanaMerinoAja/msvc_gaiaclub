package org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas;

import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.productos.RecompensaProductoDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.vales.ValeDTO;

import java.util.List;

public class RecompensasResponseDTOF {
    private boolean periodo;
    private List<RecompensaProductoDTO> recompensasProducto;
    private List<ValeDTO> recompensasVale;

    // Constructor
    public RecompensasResponseDTOF(List<RecompensaProductoDTO> recompensasProducto, List<ValeDTO> recompensasVale, boolean periodo) {
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

    public List<ValeDTO> getRecompensasVale() {
        return recompensasVale;
    }

    public void setRecompensasVale(List<ValeDTO> recompensasVale) {
        this.recompensasVale = recompensasVale;
    }
}
