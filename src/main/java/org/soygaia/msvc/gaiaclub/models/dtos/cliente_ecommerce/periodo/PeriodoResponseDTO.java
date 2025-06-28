package org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.periodo;

import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.GeneralInfoDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.panleadministracion.periodos.PeriodoDTO;

public class PeriodoResponseDTO {
    private boolean success;
    private boolean isCurrent;
    private PeriodoDTO periodo;
    private GeneralInfoDTO generalInfo;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }

    public PeriodoDTO getPeriodo() {
        return periodo;
    }

    public void setPeriodo(PeriodoDTO periodo) {
        this.periodo = periodo;
    }

    public GeneralInfoDTO getGeneralInfo() {
        return generalInfo;
    }

    public void setGeneralInfo(GeneralInfoDTO generalInfo) {
        this.generalInfo = generalInfo;
    }

}
