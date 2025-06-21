package org.soygaia.msvc.gaiaclub.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.RecompensaDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.RecompensaResponseDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.RecompensasResponseDTOF;
import org.soygaia.msvc.gaiaclub.models.entity.PeriodoEntity;
import org.soygaia.msvc.gaiaclub.services.PeriodoService;
import org.soygaia.msvc.gaiaclub.services.RecompensaService;
import org.soygaia.msvc.gaiaclub.services.ValesService;

import java.util.Map;
import java.util.Optional;

@Path("/recompensa")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RecompensaController {

    @Inject
    RecompensaService recompensaService;

    @Inject
    ValesService valesService;

    @Inject
    PeriodoService periodoService;

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
        Optional<PeriodoEntity> optionalPeriodo = Optional.ofNullable(periodoService.getCurrentPeriod());
        if(optionalPeriodo.isPresent()) {
            RecompensasResponseDTOF recompensasResponseDTOF = new RecompensasResponseDTOF(recompensaService.recompensasPeriodo(), valesService.listarValesPorPeriodo(optionalPeriodo.get().getId()),true);
            return  Response.status(Response.Status.OK).entity(recompensasResponseDTOF).build();
        }
        return  Response.status(Response.Status.NOT_FOUND).build();
    }

}
