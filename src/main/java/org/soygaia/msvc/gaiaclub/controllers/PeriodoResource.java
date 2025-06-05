package org.soygaia.msvc.gaiaclub.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.soygaia.msvc.gaiaclub.models.dtos.periodo.PeriodoActualDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.periodo.PeriodoCreationResponseDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.periodo.PeriodoDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.periodo.PeriodoResponseDTO;
import org.soygaia.msvc.gaiaclub.models.entity.PeriodoEntity;
import org.soygaia.msvc.gaiaclub.services.PeriodoService;

import java.util.Map;

@Path("/periodo")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PeriodoResource {

    @Inject
    PeriodoService periodoService;

    @POST
    @Path("/registrar")
    public Response registrarPeriodo(@Valid PeriodoDTO dto) {
        PeriodoCreationResponseDTO periodoResponse = periodoService.registrarPeriodo(dto);

        return Response.status(periodoResponse.isEstado() ? Response.Status.CREATED : Response.Status.CONFLICT).entity(Map.of(
                "response", periodoResponse
        )).build();
    }

    @GET
    @Path("/current-period")
    public Response getCurrentPeriod() {
        PeriodoEntity periodoActual = periodoService.getCurrentPeriod();
        PeriodoEntity periodoProximo = null;

        PeriodoResponseDTO response = new PeriodoResponseDTO();

        response.setPuntosCompra(puntosPorCompra);
        response.setValorCompra(valorCompra);

        if (periodoActual != null) {
            PeriodoActualDTO dto = new PeriodoActualDTO();
            dto.setNombre(periodoActual.getNombre());
            dto.setDescripcion(periodoActual.getDescripcion());
            dto.setFechaFin(periodoActual.getFechaFin());
            dto.setFechaInicio(periodoActual.getFechaInicio());
            dto.setIdPeriodo(periodoActual.getId());
            response.setCurrent(true);
            response.setPeriodo(dto);
            response.setSuccess(true);

            return Response.ok(response).build();
        }

        // Si no hay periodo activo, buscamos el más próximo a iniciar
        periodoProximo = periodoService.getNextPeriod();

        if (periodoProximo != null) {
            PeriodoActualDTO dto = new PeriodoActualDTO();
            dto.setNombre(periodoProximo.getNombre());
            dto.setDescripcion(periodoProximo.getDescripcion());
            dto.setFechaFin(periodoProximo.getFechaFin()); // para el countdown usamos inicio
            dto.setFechaInicio(periodoProximo.getFechaInicio());
            dto.setIdPeriodo(periodoProximo.getId());
            dto.setValorPunto(periodoProximo.getValorPunto());
            response.setCurrent(false);
            response.setPeriodo(dto);
            response.setSuccess(true);
            return Response.ok(response).build();
        }

        response.setSuccess(false);
        response.setPeriodo(null);
        response.setCurrent(false);
        // Ningún periodo disponible
        return Response.status(Response.Status.NOT_FOUND)
                .entity(response)
                .build();
    }

    @ConfigProperty(name = "gaia.puntos.valor-de-compra", defaultValue = "10")
    double valorCompra;

    @ConfigProperty(name = "gaia.puntos.puntos-por-compra", defaultValue = "10")
    double puntosPorCompra;

    @GET
    @Path("/valor-por-compra")
    public Response getValorCompra(){
        return Response.status(Response.Status.OK).entity(Map.of("valorCompra", valorCompra, "puntosPorCompra", puntosPorCompra)).build();
    }
}
