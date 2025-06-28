package org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.vales;

public class ValeClienteEcommerceDTO {
    private long idValeCliente;
    private long idValePeriodo;
    private long idMiembro;
    private String fechaCaducidad;
    private int puntos;
    private ValePeriodoEcommerceDTO vale;

    public ValeClienteEcommerceDTO(long idValeCliente, long idValePeriodo, long idMiembro, String fechaCaducidad, int puntos, ValePeriodoEcommerceDTO vale) {
        this.idValeCliente = idValeCliente;
        this.idValePeriodo = idValePeriodo;
        this.idMiembro = idMiembro;
        this.fechaCaducidad = fechaCaducidad;
        this.puntos = puntos;
        this.vale = vale;
    }

    public long getIdValeCliente() {
        return idValeCliente;
    }

    public void setIdValeCliente(long idValeCliente) {
        this.idValeCliente = idValeCliente;
    }

    public long getIdValePeriodo() {
        return idValePeriodo;
    }

    public void setIdValePeriodo(long idValePeriodo) {
        this.idValePeriodo = idValePeriodo;
    }

    public long getIdMiembro() {
        return idMiembro;
    }

    public void setIdMiembro(long idMiembro) {
        this.idMiembro = idMiembro;
    }

    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public ValePeriodoEcommerceDTO getVale() {
        return vale;
    }

    public void setVale(ValePeriodoEcommerceDTO vale) {
        this.vale = vale;
    }
}
