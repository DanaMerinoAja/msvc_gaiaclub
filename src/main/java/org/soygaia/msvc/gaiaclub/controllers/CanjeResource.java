package org.soygaia.msvc.gaiaclub.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.soygaia.msvc.gaiaclub.models.dtos.CanjeDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.CanjeRequestDTO;
import org.soygaia.msvc.gaiaclub.services.CanjeService;

import java.util.Map;

@Path("/canjes")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CanjeResource {

    @Inject
    CanjeService canjeService;

    @POST
    @Transactional
    public Response registrarCanje(@Valid CanjeRequestDTO dto) {
        try {
            CanjeDTO respuesta = canjeService.registrarCanje(dto.getIdCliente(), dto.getIdRecompensa());
            return Response.ok(respuesta).build();
        } catch (IllegalStateException stockRecompensaException){
            return Response.status(Response.Status.BAD_REQUEST).entity(Map.of("mensaje","Stock de recompensa agotado")).build();
        } catch (NotFoundException recompensaNoExiste){
            return Response.status(Response.Status.BAD_REQUEST).entity(Map.of("mensaje","Recompensa no existe")).build();
        }
    }
}
