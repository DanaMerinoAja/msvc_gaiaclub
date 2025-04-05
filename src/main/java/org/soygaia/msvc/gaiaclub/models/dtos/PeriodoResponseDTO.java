package org.soygaia.msvc.gaiaclub.models.dtos;

public class PeriodoResponseDTO {
    private boolean isCurrent;
    private PeriodoActualDTO periodo;
    private boolean success;

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

    public PeriodoActualDTO getPeriodo() {
        return periodo;
    }

    public void setPeriodo(PeriodoActualDTO periodo) {
        this.periodo = periodo;
    }
}
