package org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.vales;

public class EstadoCanjeVale {
    private boolean exitoso;
    private ValeClienteDTO vale;

    public EstadoCanjeVale(boolean exitoso, ValeClienteDTO vale) {
        this.exitoso = exitoso;
        this.vale = vale;
    }

    public boolean isExitoso() {
        return exitoso;
    }

    public void setExitoso(boolean exitoso) {
        this.exitoso = exitoso;
    }

    public ValeClienteDTO getVale() {
        return vale;
    }

    public void setVale(ValeClienteDTO vale) {
        this.vale = vale;
    }
}
