package org.soygaia.msvc.gaiaclub.controllers;

import jakarta.ws.rs.Path;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.paneldashboard.CanjesEntreFechasDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.paneldashboard.ResumenDashboardDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.paneldashboard.ResumenEntreFechas;
import org.soygaia.msvc.gaiaclub.services.DashboardService;

import java.time.LocalDate;

@Path("/dashboard")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DashboardController {

    @Inject DashboardService dashboardService;

    @GET @Path("/canjes")
    public Response canjes() {
        return Response.ok(dashboardService.obtenerCanjes()).build();
    }

    @GET @Path("/recompensas/poco-stock{limite}")
    public Response recompensas(@PathParam("limite") int limite) {
        return Response.ok(dashboardService.obtenerRecompensas(limite)).build();
    }

    @GET @Path("/vales")
    public Response vales() {
        return Response.ok(dashboardService.obtenerVales()).build();
    }

    @GET
    @Path("/resumen/{limite}")
    public Response resumenDeTodo(@PathParam("limite") int limite){
        return Response.status(Response.Status.OK).entity(
                new ResumenDashboardDTO(
                        dashboardService.obtenerCanjes(),
                        dashboardService.obtenerClientes(),
                        dashboardService.obtenerPuntos(),
                        dashboardService.obtenerRecompensas(limite),
                        dashboardService.obtenerVales()
                )).build();
    }

    @GET
    @Path("/resumen-entre/{fecha1}&{fecha2}")
    public Response resumenEntre(@PathParam("fecha1") LocalDate fecha1, @PathParam("fecha2") LocalDate fecha2){
        return Response.status(Response.Status.OK).entity(dashboardService.obtenerResumenEntreFechas(fecha1, fecha2)).build();
    }
}
