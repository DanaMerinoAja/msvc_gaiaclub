package org.soygaia.msvc.gaiaclub.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.soygaia.msvc.gaiaclub.models.dtos.CanjeDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.CanjeRequestDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.CanjesRequestDTO;
import org.soygaia.msvc.gaiaclub.services.CanjeService;

import java.lang.annotation.Annotation;
import java.net.URI;
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
    public Response registrarCanje(@Valid @RequestBody CanjesRequestDTO dtos) {
        try {

            List<CanjeDTO> canjeDTOS = canjeService.registrarCanjes(dtos);
            if(canjeDTOS.isEmpty()){
                return Response.status(Response.Status.BAD_REQUEST).entity(Map.of("mensaje", "No cuenta con los puntos suficentes")).build();
            }
            return Response.ok(canjeDTOS).build();
        } catch (IllegalStateException stockRecompensaException){
            return Response.status(Response.Status.BAD_REQUEST).entity(Map.of("mensaje","Stock de recompensa agotado")).build();
        } catch (NotFoundException recompensaNoExiste){
            return Response.status(Response.Status.BAD_REQUEST).entity(Map.of("mensaje","Recompensa no existe")).build();
        }
    }

    @GET
    @Path("/ultimoscanjes-cliente/{idCliente}")
    public Response ultimosCanjes(@PathParam("idCliente") Long idCliente){
        canjeService.ultimosCanjesCliente(idCliente);
        return Response.ok().build();
    }
}
