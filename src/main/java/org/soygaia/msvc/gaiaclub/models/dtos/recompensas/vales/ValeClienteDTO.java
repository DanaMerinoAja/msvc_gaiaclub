package org.soygaia.msvc.gaiaclub.models.dtos.recompensas.vales;

import java.time.LocalDate;

public class ValeClienteDTO {
    private Long idVale;
    private Long idRecompensa;
    private Long idMiembro;
    private LocalDate fechaCaducidad;
    private RecompensaValeDTO vale;

    public Long getIdVale() {
        return idVale;
    }

    public void setIdVale(Long idVale) {
        this.idVale = idVale;
    }

    public Long getIdRecompensa() {
        return idRecompensa;
    }

    public void setIdRecompensa(Long idRecompensa) {
        this.idRecompensa = idRecompensa;
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

    public RecompensaValeDTO getVale() {
        return vale;
    }

    public void setVale(RecompensaValeDTO vale) {
        this.vale = vale;
    }
}
