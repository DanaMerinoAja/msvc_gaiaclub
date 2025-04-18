package org.soygaia.msvc.gaiaclub.models.dtos;

import java.util.List;

public class RecompensasResponseDTOF {
    private List<RecompensaProductoDTO> productos;
    private List<RecompensaValeDTO> vales;

    // Constructor
    public RecompensasResponseDTOF(List<RecompensaProductoDTO> productos, List<RecompensaValeDTO> vales) {
        this.productos = productos;
        this.vales = vales;
    }

    public List<RecompensaProductoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<RecompensaProductoDTO> productos) {
        this.productos = productos;
    }

    public List<RecompensaValeDTO> getVales() {
        return vales;
    }

    public void setVales(List<RecompensaValeDTO> vales) {
        this.vales = vales;
    }
}
