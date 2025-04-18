package org.soygaia.msvc.gaiaclub.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.soygaia.msvc.gaiaclub.config.properties.ErrorCode;
import org.soygaia.msvc.gaiaclub.models.dtos.MiembroRegistroDTO;
import org.soygaia.msvc.gaiaclub.models.entity.MiembroClubEntity;
import org.soygaia.msvc.gaiaclub.services.MiembroService;

import java.util.Map;
import java.util.Optional;

@Path("/miembros")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MiembroResource {

    @Inject
    MiembroService miembroService;

    @GET
    public Response obtenerCliente(@QueryParam("dni") @DefaultValue("0") Long dni, @QueryParam("correo") @DefaultValue("-") String correo){
        Optional<MiembroClubEntity> opMiembro = miembroService.obtenerMiembro(dni, correo);
        if(opMiembro.isPresent()){
            return Response.status(Response.Status.OK).entity(opMiembro.get()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response registrarCliente(@RequestBody MiembroRegistroDTO miembroRegistroDTO){
        MiembroClubEntity miembroClub = miembroService.registrarMiembro(miembroRegistroDTO);
        return Response.status(Response.Status.OK).entity(miembroClub).build();
        /*
        try {
            MiembroClubEntity miembroClub = miembroService.registrarMiembro(miembroRegistroDTO);
            return Response.status(Response.Status.OK).entity(miembroClub).build();
        } catch (Exception ex){
            return Response.status(Response.Status.BAD_REQUEST).entity(Map.of("mensaje", ErrorCode.REGISTER_CANJE_FAILED.getCode() + ": " + ex.getMessage())).build();
        }*/
    }
}
