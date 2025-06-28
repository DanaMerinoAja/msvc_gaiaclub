package org.soygaia.msvc.gaiaclub.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.panleadministracion.vales.ValeBaseDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.miembro.MiembroInfoActDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.vales.*;
import org.soygaia.msvc.gaiaclub.models.entity.ValeEntity;
import org.soygaia.msvc.gaiaclub.services.ValesService;

import java.util.List;

@Path("/vales")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ValesController {

    @Inject
    ValesService valesService;

    /*
    Métodos ecommerce
     */

    @GET
    @Path("/{idMiembro}")
    public Response valesCliente(@PathParam("idMiembro") Long idMiembro) {
        return Response.status(Response.Status.OK).entity(valesService.valesPorCliente(idMiembro)).build();
    }

    @POST
    @Path("/registro-vale-cliente")
    public Response registrarValeCliente(@RequestBody RegistroValeClienteDTO valeClienteDTO) {
        MiembroInfoActDTO miembroActInfo = valesService.guardarValeCliente(valeClienteDTO.getMiembroClubid(), valeClienteDTO.getValeid());
        return Response.status(Response.Status.OK).entity(miembroActInfo).build();
    }

    @POST
    @Path("/registro-vale-canjeado")
    public Response registrarValeCcanjeado(@RequestBody ValeClienteDTO valeClienteDTO) {
        EstadoCanjeVale vcDTO = valesService.canjearVale(valeClienteDTO);
        return Response.status(Response.Status.OK).entity(vcDTO).build();
    }

    // Listar vales activos asignados a un periodo
    @GET
    @Path("/periodo/{periodoId}")
    public List<ValePeriodoEcommerceDTO> listarValesActivosPorPeriodo(@PathParam("periodoId") Long periodoId) {
        return valesService.listarValesActivosPorPeriodo(periodoId);
    }

    /*
    Métodos admin
     */

    @GET
    @Path("/periodo-admin/{periodoId}")
    public List<ValePeriodoDTO> listarValesPorPeriodo(@PathParam("periodoId") Long periodoId) {
        return valesService.listarValesPorPeriodo(periodoId);
    }

    @GET
    @Path("/base")
    public List<ValeBaseDTO> listarVales() {
        return valesService.listarVales();
    }

    @GET
    @Path("/vales-miembro/{idMiembro}/{idPeriodo}")
    public List<ValeClienteDTO> listarValesMiembroAdmin(@PathParam("idMiembro") Long idMiembro, @PathParam("idPeriodo") Long idPeriodo) {
        return valesService.valesClientePeriodo(idMiembro, idPeriodo);
    }

    // Crear nuevo vale base
    @POST
    public Response crearValePeriodo(ValePeriodoDTO dto) {
        return Response.status(Response.Status.CREATED).entity(valesService.crearVale(dto)).build();
    }

    // Asignar vale a periodo
    @POST
    @Path("/asignar")
    public Response asignarValeAPeriodo(@RequestBody ValePeriodoDTO valePeriodoDTO) {
        return Response.status(Response.Status.CREATED).entity(valesService.asignarValeAPeriodo(valePeriodoDTO.getValeBase().getValeBaseId(), valePeriodoDTO.getPeriodoId())).build();
    }

    // Desasignar vale a periodo
    @DELETE
    @Path("/desasignar/{valePeriodoId}")
    public Response eliminarValeAPeriodo(@PathParam("valePeriodoId") Long valePeriodoId) {
        return Response.status(Response.Status.OK).entity(valesService.eliminarValePeriodo(valePeriodoId)).build();
    }

    @PUT
    @Path("/set-estado")
    public Response setEstadoValeAPeriodo(@RequestBody ValePeriodoDTO valePeriodo) {
        return Response.status(Response.Status.OK).entity(valesService.setValePeriodo(valePeriodo.getValePeriodoId())).build();
    }
}