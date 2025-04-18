package org.soygaia.msvc.gaiaclub.models.entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PRODUCTO")
public class RecompensaProductoEntity extends RecompensaEntity {

    @Column(name = "rec_producto")
    private Long productoId;

    @Column(name = "rec_aportesoles")
    private Double aporteSoles;

    // Puedes mapear luego el producto en el servicio si necesitas enriquecer


    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public Double getAporteSoles() {
        return aporteSoles;
    }

    public void setAporteSoles(Double aporteSoles) {
        this.aporteSoles = aporteSoles;
    }
}

