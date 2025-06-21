package org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.vales;

import java.time.LocalDate;

public class ValeClienteDTO {
    private Long idValeCliente;
    private Long idValePeriodo;
    private Long idMiembro;
    private LocalDate fechaCaducidad;
    private Integer puntos;
    private ValeDTO vale;

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public Long getIdValeCliente() {
        return idValeCliente;
    }

    public void setIdValeCliente(Long idValeCliente) {
        this.idValeCliente = idValeCliente;
    }

    public Long getIdValePeriodo() {
        return idValePeriodo;
    }

    public void setIdValePeriodo(Long idValePeriodo) {
        this.idValePeriodo = idValePeriodo;
    }

    public Long getIdMiembro() {
        return idMiembro;
    }

    public void setIdMiembro(Long idMiembro) {
        this.idMiembro = idMiembro;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public ValeDTO getVale() {
        return vale;
    }

    public void setVale(ValeDTO vale) {
        this.vale = vale;
    }
}
