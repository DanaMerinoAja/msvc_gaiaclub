package org.soygaia.msvc.gaiaclub.models.dtos.admin.paneldashboard;

public record ResumenEntreFechas (
        long miembrosNuevos,
        long puntosEmitidos,
        long puntosCanjeados,
        long puntosVencidos,
        long totalCanjes,
        long puntosCanjeadosPorCanjes,
        long valesCanjeados,
        long valesAplicados
){}