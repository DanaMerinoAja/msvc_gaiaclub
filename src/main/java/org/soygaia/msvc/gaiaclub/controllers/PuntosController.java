package org.soygaia.msvc.gaiaclub.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.puntos.PuntosRegistroDTO;
import org.soygaia.msvc.gaiaclub.models.entity.PuntosEntity;
import org.soygaia.msvc.gaiaclub.services.PuntosService;

import java.util.Map;

@Path("/puntos")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PuntosController {
    @Inject
    PuntosService puntosService;

    @POST
    @Path("/registrar")
    public Response registrarPuntos(@Valid @RequestBody PuntosRegistroDTO dto) {
        PuntosEntity puntosEntity = puntosService.registrarPuntosIn(dto);
        if(puntosEntity!=null)
            return Response.status(Response.Status.CREATED).entity(puntosEntity.getTotalPuntos()).build();
        return Response.status(Response.Status.BAD_REQUEST).entity(0).build();
    }

    @GET
    @Path("/obtener/{idCliente}")
    public Response obtenerPuntos(@PathParam("idCliente") Long idCliente) {
        Long totalPuntos = puntosService.getTotalPuntosDisponiblesPorCliente(idCliente);
        if(totalPuntos!= null){
            return Response.status(Response.Status.OK).entity(Map.of(
                    "puntos", totalPuntos
            )).build();
        }
        return Response.status(Response.Status.NO_CONTENT).entity(Map.of("mensaje", "El cliente no tiene puntos")).build();
    }
}
