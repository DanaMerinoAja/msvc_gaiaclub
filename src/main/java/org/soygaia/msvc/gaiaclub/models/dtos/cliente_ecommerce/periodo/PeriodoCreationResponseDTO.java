package org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.periodo;

import org.soygaia.msvc.gaiaclub.models.entity.PeriodoEntity;

import java.util.List;

public class PeriodoCreationResponseDTO {
    private List<PeriodoEntity> periodosCruzados;
    private PeriodoEntity periodoCreado;
    private boolean estado;

    // Constructor
    public PeriodoCreationResponseDTO(List<PeriodoEntity> periodosCruzados, PeriodoEntity periodoCreado, boolean estado) {
        this.periodosCruzados = periodosCruzados;
        this.periodoCreado = periodoCreado;
        this.estado = estado;
    }

    public List<PeriodoEntity> getPeriodosCruzados() {
        return periodosCruzados;
    }

    public void setPeriodosCruzados(List<PeriodoEntity> periodosCruzados) {
        this.periodosCruzados = periodosCruzados;
    }

    public PeriodoEntity getPeriodoCreado() {
        return periodoCreado;
    }

    public void setPeriodoCreado(PeriodoEntity periodoCreado) {
        this.periodoCreado = periodoCreado;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
