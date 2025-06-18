package org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.canjes;

import java.time.LocalDate;
import java.util.List;

public class CanjeGetDTO {
    private Long idCanje;
    private LocalDate fechaCanje;
    private int totalPuntos;
    private List<DetalleCanjeGet> detallesCanje;
}
