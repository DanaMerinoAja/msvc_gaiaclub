package org.soygaia.msvc.gaiaclub.models.entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("VALE")
public class ValeEntity extends RecompensaEntity {

    @Column(name = "rec_aportesoles")
    private Double descuentoSoles;

    @Column(name = "rec_vigencia")
    private int vigencia;

    public Double getDescuentoSoles() {
        return descuentoSoles;
    }

    public void setDescuentoSoles(Double descuentoSoles) {
        this.descuentoSoles = descuentoSoles;
    }

    public int getVigencia() {
        return vigencia;
    }

    public void setVigencia(int vigencia) {
        this.vigencia = vigencia;
    }
}
