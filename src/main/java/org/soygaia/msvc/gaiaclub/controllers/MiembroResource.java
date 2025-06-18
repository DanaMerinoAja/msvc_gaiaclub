package org.soygaia.msvc.gaiaclub.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.miembro.MiembroGetDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.miembro.MiembroRegistroDTO;
import org.soygaia.msvc.gaiaclub.services.MiembroService;

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
        Optional<MiembroGetDTO> opMiembro = miembroService.obtenerMiembro(dni, correo);
        if(opMiembro.isPresent()){
            return Response.status(Response.Status.OK).entity(opMiembro.get()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response registrarCliente(@RequestBody MiembroRegistroDTO miembroRegistroDTO){
        MiembroGetDTO miembroClub = miembroService.registrarMiembro(miembroRegistroDTO);
        return Response.status(Response.Status.OK).entity(miembroClub).build();
    }

    @GET
    @Path("puntos-miembros")
    //aún no se como funciona ;) @RequestScoped
    public Response obtenerClientesAdmin(){

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
