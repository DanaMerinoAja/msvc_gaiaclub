package org.soygaia.msvc.gaiaclub.models.dtos.admin.paneldashboard;

public record CanjesDashboardDTO(
        long totalCanjes,
        long canjesHoy,
        long canjesPeriodoActual,
        long puntosCanjeadosPeriodo,
        long recompensasCanjeadosPeriodo
) {}