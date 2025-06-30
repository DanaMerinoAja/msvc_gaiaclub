package org.soygaia.msvc.gaiaclub.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.panleadministracion.GeneralInfoAdminDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.panleadministracion.periodos.PeriodoDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.panleadministracion.periodos.PeriodoCreationResponseDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.panleadministracion.periodos.PeriodoCreationDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.periodo.PeriodoResponseDTO;
import org.soygaia.msvc.gaiaclub.models.entity.PeriodoEntity;
import org.soygaia.msvc.gaiaclub.services.GeneralInfoService;
import org.soygaia.msvc.gaiaclub.services.PeriodoService;

@Path("/periodo")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PeriodoResource {

    @Inject
    PeriodoService periodoService;

    @Inject
    GeneralInfoService generalInfoService;

    /*
    Endpoints de administración
     */

    @POST
    @Path("/registrar")
    public Response registrarPeriodo(@Valid PeriodoCreationDTO dto) {
        PeriodoCreationResponseDTO periodoResponse = periodoService.registrarPeriodo(dto);
        return Response.status(periodoResponse.isEstado() ? Response.Status.CREATED : Response.Status.CONFLICT).entity(periodoResponse).build();
    }

    @PUT
    @Path("/modificar-periodo-{idPeriodo}")
    public Response modificarPeriodo(@RequestBody @Valid PeriodoCreationDTO periodo, @PathParam("idPeriodo") Long periodoId){
        return Response.status(Response.Status.OK).entity(periodoService.modificarPeriodo(periodo, periodoId)).build();
    }

    @POST
    @Path("/general-info")
    public Response actualizarInformacionGeneral(@RequestBody GeneralInfoAdminDTO genInfDTO){
        return Response.status(Response.Status.CREATED).entity(generalInfoService.actualizarInfoGeneralAdmin(genInfDTO)).build();
    }

    @GET
    @Path("/general-info")
    public Response obtenerInformacionGeneralAdmin(){
        return Response.status(Response.Status.CREATED).entity(generalInfoService.getGenInfoAdmin()).build();
    }

    @GET
    @Path("/periodos")
    public Response obtenerPeriodos(){
        return Response.status(Response.Status.OK).entity(periodoService.findAll()).build();
    }

    /*
    Endpoints ecommerce
     */
    @GET
    @Path("/current-period")
    public Response getCurrentPeriod() {
        PeriodoEntity periodoActual = periodoService.getCurrentPeriod();
        PeriodoEntity periodoProximo = null;

        PeriodoResponseDTO response = new PeriodoResponseDTO();

        response.setGeneralInfo(generalInfoService.getGenInfoCliente());

        if (periodoActual != null) {
            PeriodoDTO dto = new PeriodoDTO();
            dto.setNombre(periodoActual.getNombre());
            dto.setDescripcion(periodoActual.getDescripcion());
            dto.setFechaFin(periodoActual.getFechaFin());
            dto.setFechaInicio(periodoActual.getFechaInicio());
            dto.setIdPeriodo(periodoActual.getId());
            response.setCurrent(true);
            response.setPeriodo(dto);
            response.setSuccess(true);

            return Response.ok(response).build();
        }

        // Si no hay periodo activo, buscamos el más próximo a iniciar
        periodoProximo = periodoService.getNextPeriod();

        if (periodoProximo != null) {
            PeriodoDTO dto = new PeriodoDTO();
            dto.setNombre(periodoProximo.getNombre());
            dto.setDescripcion(periodoProximo.getDescripcion());
            dto.setFechaFin(periodoProximo.getFechaFin()); // para el countdown usamos inicio
            dto.setFechaInicio(periodoProximo.getFechaInicio());
            dto.setIdPeriodo(periodoProximo.getId());
            response.setCurrent(false);
            response.setPeriodo(dto);
            response.setSuccess(true);
            return Response.ok(response).build();
        }

        response.setSuccess(false);
        response.setPeriodo(null);
        response.setCurrent(false);
        // Ningún periodo disponible
        return Response.status(Response.Status.NOT_FOUND)
                .entity(response)
                .build();
    }

    @DELETE
    @Path("/eliminar-{idPeriodo}")
    public Response eliminarPeriodo(@PathParam("idPeriodo") Long idPeriodo){
        return Response.status(Response.Status.OK).entity(periodoService.eliminarPeriodo(idPeriodo)).build();
    }
}
