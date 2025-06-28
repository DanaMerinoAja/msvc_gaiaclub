package org.soygaia.msvc.gaiaclub.models.dtos.admin.paneldashboard;

public record PuntosDashboardDTO(
        long totalPuntosEmitidos,
        long totalPuntosEmitidosHoy,
        long totalPuntosCaducados,
        long totalPuntosCanjeados,
        long totalCanjeadosHoy,
        long totalPuntosCanjeadosPeriodo,
        long totalPuntosEmitidosPeriodo
) {}

