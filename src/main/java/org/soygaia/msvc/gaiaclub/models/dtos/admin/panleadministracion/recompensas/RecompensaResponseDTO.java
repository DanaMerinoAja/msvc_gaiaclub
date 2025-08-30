package org.soygaia.msvc.gaiaclub.models.dtos.admin.panleadministracion.recompensas;

import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.productos.RecompensaProductoDTO;
import org.soygaia.msvc.gaiaclub.models.entity.RecompensaEntity;

public class RecompensaResponseDTO {
    private RecompensaProductoDTO recompensasExiste;
    private RecompensaProductoDTO recompensaCreada;
    private boolean creada;

    public RecompensaResponseDTO(RecompensaProductoDTO recompensasExiste, RecompensaProductoDTO recompensaCreada, boolean creada) {
        this.recompensasExiste = recompensasExiste;
        this.recompensaCreada = recompensaCreada;
        this.creada = creada;
    }

    public RecompensaProductoDTO getRecompensasExiste() {
        return recompensasExiste;
    }

    public void setRecompensasExiste(RecompensaProductoDTO recompensasExiste) {
        this.recompensasExiste = recompensasExiste;
    }

    public RecompensaProductoDTO getRecompensaCreada() {
        return recompensaCreada;
    }

    public void setRecompensaCreada(RecompensaProductoDTO recompensaCreada) {
        this.recompensaCreada = recompensaCreada;
    }

    public boolean isCreada() {
        return creada;
    }

    public void setCreada(boolean creada) {
        this.creada = creada;
    }
}
