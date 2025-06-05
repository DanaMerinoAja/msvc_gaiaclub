package org.soygaia.msvc.gaiaclub.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import net.bytebuddy.asm.Advice;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.soygaia.msvc.gaiaclub.models.dtos.recompensas.vales.RecompensaValeDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.recompensas.vales.RegistroValeClienteDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.recompensas.vales.ValeClienteDTO;
import org.soygaia.msvc.gaiaclub.models.entity.RecompensaEntity;
import org.soygaia.msvc.gaiaclub.models.entity.ValeClienteEntity;
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

    @GET
    @Path("/{idMiembro}")
    public Response valesCliente(@PathParam("idMiembro") Long idMiembro){
        List<ValeClienteEntity> listVales =valesService.valesPorCliente(idMiembro);

        if(listVales.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        List<ValeClienteDTO> listValesCliente = listVales.stream().map(v -> {
            ValeClienteDTO valeClienteDTO = new ValeClienteDTO();
            valeClienteDTO.setIdMiembro(v.getMiembroClub().getIdMiembro());
            valeClienteDTO.setIdRecompensa(v.getVale().getRecId());
            valeClienteDTO.setIdVale(v.getId());
            ValeEntity vale = (ValeEntity) v.getVale();
            valeClienteDTO.setVale(new RecompensaValeDTO(vale.getRecId(), vale.getDescuentoSoles(), vale.getNombre(), vale.getDescripcion(), vale.getVigencia(), vale.getPuntosRequeridos()));
            valeClienteDTO.setFechaCaducidad(v.getFechaCaducidad());
            return valeClienteDTO;
        }).toList();

        return Response.status(Response.Status.OK).entity(listValesCliente).build();
    }

    @POST
    @Path("/registro-vale-cliente")
    public Response registrarValeCliente(@RequestBody RegistroValeClienteDTO valeClienteDTO){
        ValeClienteEntity v = valesService.guardarValeCliente(valeClienteDTO.getMiembroClubid(), valeClienteDTO.getValeid());
        ValeClienteDTO vcDTO = new ValeClienteDTO();
        vcDTO.setIdMiembro(v.getMiembroClub().getIdMiembro());
        vcDTO.setIdRecompensa(v.getVale().getRecId());
        vcDTO.setIdVale(v.getId());
        ValeEntity vale = (ValeEntity) v.getVale();
        vcDTO.setVale(new RecompensaValeDTO(vale.getRecId(), vale.getDescuentoSoles(), vale.getNombre(), vale.getDescripcion(), vale.getVigencia(), vale.getPuntosRequeridos()));
        vcDTO.setFechaCaducidad(v.getFechaCaducidad());
        return Response.status(Response.Status.OK).entity(vcDTO).build();
    }
}
