package org.soygaia.msvc.gaiaclub.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.soygaia.msvc.gaiaclub.models.dtos.recompensas.RecompensaDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.recompensas.RecompensaResponseDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.recompensas.RecompensasResponseDTOF;
import org.soygaia.msvc.gaiaclub.services.RecompensaService;

import java.util.Map;

@Path("/recompensa")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RecompensaController {

    @Inject
    RecompensaService recompensaService;

    @POST
    @Path("/registrar")
    public Response registrarRecompensa(@Valid RecompensaDTO dto) {
        RecompensaResponseDTO recompensa = recompensaService.guardarRecompensa(dto);
        return Response.status(Response.Status.CREATED).entity(Map.of(
                "mensaje", recompensa
        )).build();
    }

    @GET
    @Path("/recompensas-periodo-actual")
    public Response obtenerRecompensasVigentes(){
        RecompensasResponseDTOF recompensasPeriodo = recompensaService.recompensasPeriodo();
        return  Response.status(Response.Status.OK).entity(recompensasPeriodo).build();
    }

}
