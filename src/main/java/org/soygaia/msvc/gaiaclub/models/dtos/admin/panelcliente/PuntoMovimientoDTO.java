package org.soygaia.msvc.gaiaclub.models.dtos.admin.panelcliente;

import java.time.LocalDate;

public class PuntoMovimientoDTO {
    private LocalDate fecha;
    private String tipo; // "ACUMULACIÓN", "CANJE", "VENCIMIENTO"
    private Integer puntos;

    public PuntoMovimientoDTO(LocalDate fecha, String tipo, Integer puntos) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.puntos = puntos;
    }
}
