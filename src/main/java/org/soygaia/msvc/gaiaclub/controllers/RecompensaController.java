package org.soygaia.msvc.gaiaclub.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.panleadministracion.recompensas.RecompensaPostDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.panleadministracion.recompensas.RecompensaPutDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.panleadministracion.recompensas.RecompensaResponseDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.RecompensasResponseDTOF;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.productos.RecompensaProductoDTO;
import org.soygaia.msvc.gaiaclub.models.entity.PeriodoEntity;
import org.soygaia.msvc.gaiaclub.services.PeriodoService;
import org.soygaia.msvc.gaiaclub.services.RecompensaService;
import org.soygaia.msvc.gaiaclub.services.ValesService;

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

    /*
    Métodos admin
     */

    @POST
    @Path("/registrar")
    public Response registrarRecompensa(@Valid RecompensaPostDTO dto) {
        RecompensaResponseDTO recompensa = recompensaService.guardarRecompensa(dto);
        return Response.status(Response.Status.CREATED).entity(recompensa).build();
    }

    @PUT
    @Path("/editar")
    public Response editarRecompensa(@Valid RecompensaPutDTO dto) {
        RecompensaProductoDTO recompensa = recompensaService.actualizarRecompensa(dto);
        return Response.status(Response.Status.OK).entity(recompensa).build();
    }

    @PUT
    @Path("/{id}/stock-cero")
    public Response editarRecompensaStockCero(@PathParam("id") Long id) {
        RecompensaProductoDTO recompensa = recompensaService.stockCero(id);
        return Response.status(Response.Status.OK).entity(recompensa).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response eliminarRecompensa(@Valid Long id) {
        return Response.status(Response.Status.OK).entity(recompensaService.eliminarRecompensa(id)).build();
    }

    @GET
    @Path("/recompensas-periodo/{periodoId}")
    public Response obtenerRecompensasProducto(@PathParam("periodoId")Long periodoId){
        Optional<PeriodoEntity> optionalPeriodo = Optional.ofNullable(periodoService.getCurrentPeriod());
        if(optionalPeriodo.isPresent()) {
            return  Response.status(Response.Status.OK).entity(recompensaService.recompensasPeriodo(periodoId)).build();
        }
        return  Response.status(Response.Status.NOT_FOUND).build();
    }

    /*
    Métodos ecommerce
     */

    @GET
    @Path("/recompensas-periodo-actual")
    public Response obtenerRecompensasVigentes(){
        Optional<PeriodoEntity> optionalPeriodo = Optional.ofNullable(periodoService.getCurrentPeriod());
        if(optionalPeriodo.isPresent()) {
            RecompensasResponseDTOF recompensasResponseDTOF = new RecompensasResponseDTOF(recompensaService.recompensasPeriodoActual(), valesService.listarValesActivosPorPeriodo(optionalPeriodo.get().getId()),true);
            return  Response.status(Response.Status.OK).entity(recompensasResponseDTOF).build();
        }
        return  Response.status(Response.Status.NOT_FOUND).build();
    }

}
