package org.soygaia.msvc.gaiaclub.models.dtos.admin.panelcanjes;

import java.time.LocalDate;

public class DetalleValeCanjeDTO {
    private String nombre;
    private LocalDate vigencia;
    private Integer puntosUsados;
    private Double valorSoles;

    public DetalleValeCanjeDTO(String nombre, LocalDate vigencia, Integer puntosUsados, Double valorSoles) {
        this.nombre = nombre;
        this.vigencia = vigencia;
        this.puntosUsados = puntosUsados;
        this.valorSoles = valorSoles;
    }
}
