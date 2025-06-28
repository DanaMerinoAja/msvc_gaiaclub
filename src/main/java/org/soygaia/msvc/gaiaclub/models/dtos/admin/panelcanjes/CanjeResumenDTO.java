package org.soygaia.msvc.gaiaclub.models.dtos.admin.panelcanjes;

import org.soygaia.msvc.gaiaclub.models.entity.CanjeEntity;

import java.time.LocalDate;

public class CanjeResumenDTO {
    private Long id;
    private String nombreMiembro;
    private Long miembroID;
    private LocalDate fechaCanje;
    private Long totalPuntos;
    private String estado;

    public CanjeResumenDTO(Long id, String nombreMiembro, Long miembroID, LocalDate fechaCanje, Long totalPuntos, CanjeEntity.EstadoCanje estado) {
        this.id = id;
        this.nombreMiembro = nombreMiembro;
        this.miembroID = miembroID;
        this.fechaCanje = fechaCanje;
        this.totalPuntos = totalPuntos;
        this.estado = estado.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreMiembro() {
        return nombreMiembro;
    }

    public void setNombreMiembro(String nombreMiembro) {
        this.nombreMiembro = nombreMiembro;
    }

    public Long getMiembroID() {
        return miembroID;
    }

    public void setMiembroID(Long miembroID) {
        this.miembroID = miembroID;
    }

    public LocalDate getFechaCanje() {
        return fechaCanje;
    }

    public void setFechaCanje(LocalDate fechaCanje) {
        this.fechaCanje = fechaCanje;
    }

    public Long getTotalPuntos() {
        return totalPuntos;
    }

    public void setTotalPuntos(Long totalPuntos) {
        this.totalPuntos = totalPuntos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
