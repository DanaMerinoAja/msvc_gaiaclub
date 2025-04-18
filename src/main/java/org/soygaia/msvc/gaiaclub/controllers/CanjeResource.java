package org.soygaia.msvc.gaiaclub.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.soygaia.msvc.gaiaclub.models.dtos.CanjeRequestDTO;
import org.soygaia.msvc.gaiaclub.models.entity.CanjeEntity;
import org.soygaia.msvc.gaiaclub.services.CanjeService;

import java.util.*;

@Path("/canjes")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CanjeResource {

    @Inject
    CanjeService canjeService;

    @POST
    @Transactional
    public Response registrarCanje(@Valid @RequestBody CanjeRequestDTO dto) {
        try {
            CanjeEntity canjeEntity = canjeService.registrarCanje(dto);
            return Response.ok(canjeEntity).build();
        } catch (IllegalStateException stockRecompensaException){
            return Response.status(Response.Status.BAD_REQUEST).entity(Map.of("mensaje","Stock de recompensa agotado")).build();
        } catch (NotFoundException recompensaNoExiste){
            return Response.status(Response.Status.BAD_REQUEST).entity(Map.of("mensaje","Recompensa no existe")).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(Map.of("mensaje",ex.getMessage())).build();
        }
    }

    @GET
    @Path("/ultimoscanjes-cliente/{idCliente}/{idPeriodo}")
    public Response ultimosCanjes(@PathParam("idMiembro") Long idMiembro, @PathParam("idPeriodo") Long idPeriodo){
        canjeService.ultimosCanjesCliente(idPeriodo, idMiembro);
        return Response.ok().build();
    }
}
