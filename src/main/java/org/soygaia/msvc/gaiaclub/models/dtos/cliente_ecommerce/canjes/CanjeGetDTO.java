package org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.canjes;

import org.soygaia.msvc.gaiaclub.models.dtos.admin.panelcanjes.DetalleRecompensaCanjeDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.productos.RecompensaActInfoDTO;

import java.time.LocalDate;
import java.util.List;

public class CanjeGetDTO {
    private Long idCanje;
    private LocalDate fechaCanje;
    private int totalPuntos;
    private List<DetalleRecompensaCanjeDTO> detallesCanje;
    private List<RecompensaActInfoDTO> recsActualizadas;

    public Long getIdCanje() {
        return idCanje;
    }

    public void setIdCanje(Long idCanje) {
        this.idCanje = idCanje;
    }

    public LocalDate getFechaCanje() {
        return fechaCanje;
    }

    public void setFechaCanje(LocalDate fechaCanje) {
        this.fechaCanje = fechaCanje;
    }

    public int getTotalPuntos() {
        return totalPuntos;
    }

    public void setTotalPuntos(int totalPuntos) {
        this.totalPuntos = totalPuntos;
    }

    public List<DetalleRecompensaCanjeDTO> getDetallesCanje() {
        return detallesCanje;
    }

    public void setDetallesCanje(List<DetalleRecompensaCanjeDTO> detallesCanje) {
        this.detallesCanje = detallesCanje;
    }

    public List<RecompensaActInfoDTO> getRecsActualizadas() {
        return recsActualizadas;
    }

    public void setRecsActualizadas(List<RecompensaActInfoDTO> recsActualizadas) {
        this.recsActualizadas = recsActualizadas;
    }
}