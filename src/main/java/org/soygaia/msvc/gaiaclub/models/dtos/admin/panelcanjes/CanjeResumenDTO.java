package org.soygaia.msvc.gaiaclub.models.dtos.admin.panelcanjes;

import java.time.LocalDate;

public class CanjeResumenDTO {
    private Long id;
    private LocalDate fecha;
    private String tipo; // VALE o RECOMPENSA
    private Integer totalPuntos;
    private String estado;

    public CanjeResumenDTO(Long id, LocalDate fecha, String tipo, Integer totalPuntos, String estado) {
        this.id = id;
        this.fecha = fecha;
        this.tipo = tipo;
        this.totalPuntos = totalPuntos;
        this.estado = estado;
    }
}
