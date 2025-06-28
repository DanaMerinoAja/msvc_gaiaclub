package org.soygaia.msvc.gaiaclub.models.dtos.admin.paneldashboard;

public record ResumenDashboardDTO (
        CanjesDashboardDTO canjesDashboardDTO,
        ClientesDashboardDTO clientesDashboardDTO,
        PuntosDashboardDTO puntosDashboardDTO,
        RecompensasDashboardDTO recompensasDashboardDTO,
        ValesDashboardDTO valesDashboardDTO
) {}
