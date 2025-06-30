package org.soygaia.msvc.gaiaclub.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.panelcanjes.CanjeResumenDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.panelcanjes.DetalleRecompensaCanjeDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.panleadministracion.recompensas.RecompensaPutDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.canjes.CanjeRequestDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.miembro.MiembroInfoActDTO;
import org.soygaia.msvc.gaiaclub.services.CanjeService;

import java.util.*;

@Path("/canjes")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CanjeResource {

    @Inject
    CanjeService canjeService;

    /*
    Ecommerce
     */

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
        return Response.status(Response.Status.OK).entity(ultCanjes).build();
    }

    /*
    Admin
     */

    @GET
    @Path("/canjes-por-entregar")
    public Response canjesPorEntregar(){
        List<CanjeResumenDTO> canjes = canjeService.listaCanjesPorEntregar();
        if(canjes.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.status(Response.Status.OK).entity(canjes).build();
    }

    @GET
    @Path("/all-canjes")
    public Response allCanjes(@QueryParam("page") @DefaultValue("0") int page,
                              @QueryParam("size") @DefaultValue("10") int size) {
        List<CanjeResumenDTO> canjes = canjeService.listaAllCanjesPaginado(page, size);
        return Response.ok(canjes).build();
    }

    @GET
    @Path("/detalles-canje/{idCanje}")
    public Response detallesCanje(@PathParam("idCanje") Long idCanje) {
        List<DetalleRecompensaCanjeDTO> canjes = canjeService.detallesCanje(idCanje);
        if (canjes.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.status(Response.Status.OK).entity(canjes).build();
    }

    @PUT
    @Path("/editar")
    public Response estadoCanje(@RequestBody CanjeResumenDTO dto) {
        return Response.status(Response.Status.OK).entity(canjeService.cambiarEstado(dto)).build();
    }
}
