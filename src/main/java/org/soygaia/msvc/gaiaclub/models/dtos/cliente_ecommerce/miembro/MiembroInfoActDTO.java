package org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.miembro;

public class MiembroInfoActDTO {
    Object valeOCanje;
    Long puntosDisp;
    Long puntosVencerAct;

    public MiembroInfoActDTO(Object valeOCanje, Long puntosDisp, Long puntosVencerAct) {
        this.valeOCanje = valeOCanje;
        this.puntosDisp = puntosDisp;
        this.puntosVencerAct = puntosVencerAct;
    }

    public Object getValeOCanje() {
        return valeOCanje;
    }

    public void setValeOCanje(Object valeOCanje) {
        this.valeOCanje = valeOCanje;
    }

    public Long getPuntosDisp() {
        return puntosDisp;
    }

    public void setPuntosDisp(Long puntosDisp) {
        this.puntosDisp = puntosDisp;
    }

    public Long getPuntosVencerAct() {
        return puntosVencerAct;
    }

    public void setPuntosVencerAct(Long puntosVencerAct) {
        this.puntosVencerAct = puntosVencerAct;
    }
}
