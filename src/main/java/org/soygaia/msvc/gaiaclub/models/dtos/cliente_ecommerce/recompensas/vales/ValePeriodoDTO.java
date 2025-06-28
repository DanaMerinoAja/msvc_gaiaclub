package org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.vales;

import org.soygaia.msvc.gaiaclub.models.dtos.admin.panleadministracion.vales.ValeBaseDTO;

public class ValePeriodoDTO {
    private Long valePeriodoId;
    private Long periodoId;
    private boolean activo;
    private ValeBaseDTO valeBase;

    public ValePeriodoDTO(Long valePeriodoId, Long periodoId, boolean activo, ValeBaseDTO valeBase) {
        this.valePeriodoId = valePeriodoId;
        this.periodoId = periodoId;
        this.valeBase = valeBase;
        this.activo = activo;
    }

    public Long getValePeriodoId() {
        return valePeriodoId;
    }

    public void setValePeriodoId(Long valePeriodoId) {
        this.valePeriodoId = valePeriodoId;
    }

    public Long getPeriodoId() {
        return periodoId;
    }

    public void setPeriodoId(Long periodoId) {
        this.periodoId = periodoId;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public ValeBaseDTO getValeBase() {
        return valeBase;
    }

    public void setValeBase(ValeBaseDTO valeBase) {
        this.valeBase = valeBase;
    }
}
