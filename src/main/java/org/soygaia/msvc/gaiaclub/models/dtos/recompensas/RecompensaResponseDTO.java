package org.soygaia.msvc.gaiaclub.models.dtos.recompensas;

import org.soygaia.msvc.gaiaclub.models.entity.RecompensaEntity;

import java.util.List;

public class RecompensaResponseDTO {
    private List<RecompensaEntity> recompensasDuplicadas;
    private RecompensaEntity recompensaEntity;
    private boolean creada;

    public RecompensaResponseDTO(List<RecompensaEntity> recompensasDuplicadas, RecompensaEntity recompensaEntity, boolean creada) {
        this.recompensasDuplicadas = recompensasDuplicadas;
        this.recompensaEntity = recompensaEntity;
        this.creada = creada;
    }

    public List<RecompensaEntity> getRecompensasDuplicadas() {
        return recompensasDuplicadas;
    }

    public void setRecompensasDuplicadas(List<RecompensaEntity> recompensasDuplicadas) {
        this.recompensasDuplicadas = recompensasDuplicadas;
    }

    public RecompensaEntity getRecompensaEntity() {
        return recompensaEntity;
    }

    public void setRecompensaEntity(RecompensaEntity recompensaEntity) {
        this.recompensaEntity = recompensaEntity;
    }

    public boolean isCreada() {
        return creada;
    }

    public void setCreada(boolean creada) {
        this.creada = creada;
    }
}
