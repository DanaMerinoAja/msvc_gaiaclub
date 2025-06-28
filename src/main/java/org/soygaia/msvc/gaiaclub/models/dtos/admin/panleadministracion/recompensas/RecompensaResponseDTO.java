package org.soygaia.msvc.gaiaclub.models.dtos.admin.panleadministracion.recompensas;

import org.soygaia.msvc.gaiaclub.models.entity.RecompensaEntity;

public class RecompensaResponseDTO {
    private RecompensaEntity recompensasExiste;
    private RecompensaEntity recompensaCreada;
    private boolean creada;

    public RecompensaResponseDTO(RecompensaEntity recompensasExiste, RecompensaEntity recompensaCreada, boolean creada) {
        this.recompensasExiste = recompensasExiste;
        this.recompensaCreada = recompensaCreada;
        this.creada = creada;
    }

    public RecompensaEntity getRecompensasExiste() {
        return recompensasExiste;
    }

    public void setRecompensasExiste(RecompensaEntity recompensasExiste) {
        this.recompensasExiste = recompensasExiste;
    }

    public RecompensaEntity getRecompensaCreada() {
        return recompensaCreada;
    }

    public void setRecompensaCreada(RecompensaEntity recompensaCreada) {
        this.recompensaCreada = recompensaCreada;
    }

    public boolean isCreada() {
        return creada;
    }

    public void setCreada(boolean creada) {
        this.creada = creada;
    }
}
