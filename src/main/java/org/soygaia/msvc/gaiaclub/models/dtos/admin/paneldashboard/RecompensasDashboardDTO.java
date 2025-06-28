package org.soygaia.msvc.gaiaclub.models.dtos.admin.paneldashboard;

public record RecompensasDashboardDTO(
        long totalRecompensas,
        long disponibles,
        long sinStock,
        long pocoStock,
        long stockTodasRecompensas
) {}

