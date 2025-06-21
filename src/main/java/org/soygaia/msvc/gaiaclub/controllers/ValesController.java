package org.soygaia.msvc.gaiaclub.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.miembro.MiembroInfoActDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.vales.EstadoCanjeVale;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.vales.ValeDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.vales.RegistroValeClienteDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.vales.ValeClienteDTO;
import org.soygaia.msvc.gaiaclub.models.entity.ValeClienteEntity;
import org.soygaia.msvc.gaiaclub.models.entity.ValeEntity;
import org.soygaia.msvc.gaiaclub.models.entity.ValePeriodoEntity;
import org.soygaia.msvc.gaiaclub.services.ValesService;

import java.util.List;

@Path("/vales")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ValesController {

    @Inject
    ValesService valesService;

    @GET
    @Path("/{idMiembro}")
    public Response valesCliente(@PathParam("idMiembro") Long idMiembro) {
        List<ValeClienteEntity> listVales = valesService.valesPorCliente(idMiembro);

        if (listVales.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        List<ValeClienteDTO> listValesCliente = listVales.stream().map(v -> {
            ValeClienteDTO valeClienteDTO = new ValeClienteDTO();
            valeClienteDTO.setIdMiembro(v.getMiembro().getId());
            valeClienteDTO.setIdValePeriodo(v.getValePeriodo().getId());
            valeClienteDTO.setIdValeCliente(v.getId());
            ValePeriodoEntity vale = v.getValePeriodo();
            valeClienteDTO.setVale(new ValeDTO(vale.getId(), vale.getVale().getValorSoles(), vale.getVale().getNombre(), vale.getVale().getDescripcion(), vale.getVale().getVigenciaDias(), vale.getVale().getPuntosRequeridos(), vale.getPeriodo().getId()));
            valeClienteDTO.setFechaCaducidad(v.getFechaCaducidad());
            return valeClienteDTO;
        }).toList();

        return Response.status(Response.Status.OK).entity(listValesCliente).build();
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

    @GET
    public List<ValeDTO> listarVales() {
        return valesService.listarVales();
    }

    // Listar vales asignados a un periodo
    @GET
    @Path("/periodo/{periodoId}")
    public List<ValeDTO> listarValesPorPeriodo(@PathParam("periodoId") Long periodoId) {
        return valesService.listarValesPorPeriodo(periodoId);
    }

    // Crear nuevo vale base
    @POST
    public Response crearVale(ValeDTO dto) {
        ValeEntity nuevo = valesService.crearVale(dto);
        return Response.status(Response.Status.CREATED).entity(nuevo).build();
    }

    // 4. Editar vale existente
    @PUT
    @Path("/{id}")
    public Response editarVale(@PathParam("id") Long id, ValeDTO dto) {
        ValeEntity actualizado = valesService.editarVale(id, dto);
        return Response.ok(actualizado).build();
    }

    // 5. Eliminar vale base
    @DELETE
    @Path("/{id}")
    public Response eliminarVale(@PathParam("id") Long id) {
        valesService.eliminarVale(id);
        return Response.noContent().build();
    }

    // 6. Asignar vale a periodo
    @POST
    @Path("/asignar")
    public Response asignarValeAPeriodo(@QueryParam("valeId") Long valeId,
                                        @QueryParam("periodoId") Long periodoId) {
        ValePeriodoEntity asignado = valesService.asignarValeAPeriodo(valeId, periodoId);
        return Response.status(Response.Status.CREATED).entity(asignado.getId()).build();
    }

    // 7. Eliminar vínculo vale-periodo
    @DELETE
    @Path("/asignacion/{valePeriodoId}")
    public Response eliminarAsignacionPeriodo(@PathParam("valePeriodoId") Long valePeriodoId) {
        valesService.eliminarAsignacionDePeriodo(valePeriodoId);
        return Response.noContent().build();
    }

}