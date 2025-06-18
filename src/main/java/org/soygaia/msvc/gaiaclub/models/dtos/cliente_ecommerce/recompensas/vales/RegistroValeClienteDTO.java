package org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.vales;

public class RegistroValeClienteDTO {
    private Long valeid;
    private Long miembroClubid;

    public Long getValeid() {
        return valeid;
    }

    public void setValeid(Long valeid) {
        this.valeid = valeid;
    }

    public Long getMiembroClubid() {
        return miembroClubid;
    }

    public void setMiembroClubid(Long miembroClubid) {
        this.miembroClubid = miembroClubid;
    }
}
