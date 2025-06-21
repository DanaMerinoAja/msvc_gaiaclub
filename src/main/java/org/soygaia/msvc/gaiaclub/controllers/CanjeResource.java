package org.soygaia.msvc.gaiaclub.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.panelcanjes.DetalleRecompensaCanjeDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.canjes.CanjeRequestDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.miembro.MiembroInfoActDTO;
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
    public Response registrarCanjeRecompensa(@Valid @RequestBody CanjeRequestDTO dto) {
        try {
            MiembroInfoActDTO canjeEntity = canjeService.registrarCanjeRecompensa(dto);
            return Response.ok(canjeEntity).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(Map.of("mensaje",ex.getMessage())).build();
        }
    }

    @GET
    @Path("/ultimoscanjes-cliente/{idMiembro}/{idPeriodo}")
    public Response ultimosCanjes(@PathParam("idMiembro") Long idMiembro, @PathParam("idPeriodo") Long idPeriodo){
        List<DetalleRecompensaCanjeDTO> ultCanjes = canjeService.ultimosCanjesCliente(idPeriodo, idMiembro);
        if(ultCanjes.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.status(Response.Status.OK).entity(ultCanjes).build();
    }
}
