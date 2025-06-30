package org.soygaia.msvc.gaiaclub.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.paneldashboard.*;
import org.soygaia.msvc.gaiaclub.models.entity.PeriodoEntity;
import org.soygaia.msvc.gaiaclub.repositories.*;

import java.time.LocalDate;

@ApplicationScoped
public class DashboardService {

    @Inject
    MiembroRepository miembroRepo;
    @Inject
    PuntosRepository puntosRepo;
    @Inject
    CanjeRepository canjeRepo;
    @Inject
    RecompensaRepository recompensaRepo;
    @Inject
    ValePeriodoRepository valeRepo;
    @Inject
    ValeClienteRepository valeClienteRepository;
    @Inject
    PeriodoService periodoService;

    public ClientesDashboardDTO obtenerClientes() {
        LocalDate hoy = LocalDate.now();
        PeriodoEntity periodo = periodoService.getCurrentPeriod();
        long periodoId = periodo != null ? periodo.getId() : 0;
        return new ClientesDashboardDTO(
                miembroRepo.contarTotalMiembros(),
                miembroRepo.contarMiembrosPorFecha(hoy),
                periodoId != 0 ? miembroRepo.contarClientesConCanjes(periodoId):0
        );
    }

    public long obtenerClientesEntreFechas(LocalDate fecha1, LocalDate fecha2) {
        return miembroRepo.contarMiembrosEntreFechas(fecha1, fecha2);
    }

    public PuntosDashboardDTO obtenerPuntos() {
        PeriodoEntity pe = periodoService.getCurrentPeriod();
        return new PuntosDashboardDTO(
                puntosRepo.totalPuntos(),
                puntosRepo.totalPuntosEmitidodHoy(),
                puntosRepo.totalPuntosVencidos(),
                puntosRepo.totalPuntosCanjeados(),
                puntosRepo.totalPuntosCanjeadosHoy(),
                pe != null ? puntosRepo.puntosCanjeadosEntreFechas(pe.getFechaInicio(), pe.getFechaFin()) : 0,
                pe != null ? puntosRepo.puntosEmitidosEntreFechas(pe.getFechaInicio(), pe.getFechaFin()) : 0,
                pe != null ? puntosRepo.puntosBonificacionesEntreFechas(pe.getFechaInicio(), pe.getFechaFin()) : 0
        );
    }

    public CanjesDashboardDTO obtenerCanjes() {
        LocalDate hoy = LocalDate.now();
        PeriodoEntity periodo = periodoService.getCurrentPeriod();
        long periodoId = periodo != null ? periodo.getId():0;
        return new CanjesDashboardDTO(
                canjeRepo.totalCanjes(),
                canjeRepo.canjesPorFecha(hoy),
                periodoId != 0 ? canjeRepo.totalCanjesPeriodo(periodoId) : 0,
                periodoId != 0 ? canjeRepo.puntosCanjeadosPeriodo(periodoId) : 0,
                periodoId != 0 ? canjeRepo.recompensasCanjeadasEntreFechas(periodo.getFechaInicio(), periodo.getFechaFin()) : 0
        );
    }


    public RecompensasDashboardDTO obtenerRecompensas(int limite) {
        PeriodoEntity periodo = periodoService.getCurrentPeriod();
        long periodoId = periodo != null ? periodo.getId():0;
        return new RecompensasDashboardDTO(
                periodoId != 0 ? recompensaRepo.totalRecompensasPeriodo(periodoId) : 0,
                periodoId != 0 ? recompensaRepo.recompensasDisponiblesPeriodo(periodoId) : 0,
                periodoId != 0 ? recompensaRepo.recompensasSinStock(periodoId) : 0,
                periodoId != 0 ? recompensaRepo.recompensasPocoStock(periodoId, limite) : 0,
                periodoId != 0 ? recompensaRepo.recompensasStockTotalActual(periodoId) : 0

        );
    }

    public ValesDashboardDTO obtenerVales() {
        PeriodoEntity periodo = periodoService.getCurrentPeriod();
        long periodoId = periodo != null ? periodo.getId():0;
        return new ValesDashboardDTO(
                periodoId != 0 ? valeRepo.valesActivosPorPeriodo(periodoId) : 0,
                periodoId != 0 ? valeClienteRepository.contarValesCanjeadosPeriodoEntreFechas(periodo.getFechaInicio(), periodo.getFechaFin()) : 0,
                periodoId != 0 ? valeClienteRepository.contarValesAplicadosPeriodoEntreFechas(periodo.getFechaInicio(), periodo.getFechaFin()) : 0
        );
    }

    public ResumenEntreFechas obtenerResumenEntreFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        return new ResumenEntreFechas(
                obtenerClientesEntreFechas(fechaInicio, fechaFin),
                puntosRepo.puntosEmitidosEntreFechas(fechaInicio, fechaFin),
                puntosRepo.puntosCanjeadosEntreFechas(fechaInicio, fechaFin),
                puntosRepo.puntosVencidosEntreFechas(fechaInicio, fechaFin),
                puntosRepo.puntosBonificacionesEntreFechas(fechaInicio, fechaFin),
                canjeRepo.totalCanjesEntreFechas(fechaInicio, fechaFin),
                canjeRepo.puntosCanjeadosEntreFechas(fechaInicio, fechaFin),
                valeClienteRepository.contarValesCanjeadosPeriodoEntreFechas(fechaInicio, fechaFin),
                valeClienteRepository.contarValesAplicadosPeriodoEntreFechas(fechaInicio, fechaFin),
                canjeRepo.recompensasCanjeadasEntreFechas(fechaInicio, fechaFin),
                recompensaRepo.recompensasNoCanjeadasEntreFechas(fechaInicio, fechaFin)
        );
    }


}
