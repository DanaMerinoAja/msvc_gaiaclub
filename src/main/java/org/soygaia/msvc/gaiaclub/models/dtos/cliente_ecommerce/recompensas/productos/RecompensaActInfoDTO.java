package org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.productos;

public class RecompensaActInfoDTO {
    private Long idRec;
    private Integer newStock;

    public RecompensaActInfoDTO(Long idRec, Integer newStock) {
        this.idRec = idRec;
        this.newStock = newStock;
    }

    public Long getIdRec() {
        return idRec;
    }

    public void setIdRec(Long idRec) {
        this.idRec = idRec;
    }

    public Integer getNewStock() {
        return newStock;
    }

    public void setNewStock(Integer newStock) {
        this.newStock = newStock;
    }
}
