package org.soygaia.msvc.gaiaclub.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.bonificaciones.BonificacionDTO;
import org.soygaia.msvc.gaiaclub.services.BonificacionService;
import org.soygaia.msvc.gaiaclub.services.PuntosService;

import java.util.Map;

@Path("/bonificaciones")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BonificacionResource {

    @Inject
    BonificacionService service;

    @Inject
    PuntosService puntosService;

    @GET
    public Response bonificacionesActivas(){
        return Response.status(Response.Status.OK).entity(service.findAll()).build();
    }

    @GET
    @Path("/historial{id}")
    public Response historialBoni(@PathParam("id") Long idBonificacion){
        return Response.status(Response.Status.OK).entity(service.obtenerHistorial(idBonificacion)).build();
    }

    @POST
    @Path("crear")
    public Response crearBonificacion(BonificacionDTO dto){
        return Response.status(Response.Status.OK).entity(service.crearBonificacion(dto)).build();
    }

    @POST
    @Path("aplicar{idBonificacion}a{miembroDNI}")
    public Response aplicarBonificacion(@PathParam("idBonificacion") Long idBonificacion, @PathParam("miembroDNI") String dniMiembro){
        return Response.status(Response.Status.OK).entity(Map.of("mensaje", puntosService.registrarPuntosBonificacion(dniMiembro, idBonificacion))).build();
    }

    @PUT
    @Path("modificar")
    public Response modificarBonificacion(BonificacionDTO bonificacion){
        return Response.status(Response.Status.OK).entity(Map.of("bonificacion", service.modificarBonificacion(bonificacion), "idBonificacionAnterior", bonificacion.getId())).build();
    }

    @PUT
    @Path("restaurar_de{idBonificacionActual}_a{idBonificacionAnterior}")
    public Response restaurarBonificacion(@PathParam("idBonificacionActual") Long idBonificacionActual, @PathParam("idBonificacionAnterior") Long idBonificacionAnterior){
        return Response.status(Response.Status.OK).entity(service.restaurarAnterior(idBonificacionActual, idBonificacionAnterior)).build();
    }

    @PUT
    @Path("settear-estado-{idBonificacion}")
    public Response setEstadoBonificacion(@PathParam("idBonificacion") Long idBonificacion){
        return Response.status(Response.Status.OK).entity(service.setEstadoBonificacion(idBonificacion)).build();
    }

    /*
    Bonificaciones ecommerce
     */

    @GET
    @Path("activas-cliente")
    public Response getBonificacionesActivas(){
        return Response.status(Response.Status.OK).entity(service.getBonificacionesActivas()).build();
    }
}
