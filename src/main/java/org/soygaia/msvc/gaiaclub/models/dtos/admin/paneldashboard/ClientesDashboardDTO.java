package org.soygaia.msvc.gaiaclub.models.dtos.admin.paneldashboard;

public record ClientesDashboardDTO(
        long totalRegistrados,
        long nuevosHoy,
        long clientesActivos
) {}
