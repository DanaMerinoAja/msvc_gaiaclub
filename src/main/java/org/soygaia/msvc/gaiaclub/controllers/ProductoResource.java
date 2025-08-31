package org.soygaia.msvc.gaiaclub.controllers;

import io.quarkus.logging.Log;
import io.vertx.core.http.HttpClientOptions;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.soygaia.msvc.gaiaclub.clients.ProductoResourceClient;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.productos.RecompensaProductoDTO;
import org.soygaia.msvc.gaiaclub.services.ProductoClientService;

import java.io.IOException;
import java.util.List;

@Path("/precio-venta")
@Produces(MediaType.APPLICATION_JSON)
public class ProductoResource {

    @Inject
    @RestClient
    ProductoResourceClient cliente;

    @Inject
    JsonWebToken jwt;

    @Inject
    ProductoClientService productoClientService;

    @GET
    public Response consultar(@QueryParam("currentPage") @DefaultValue("0") int page,
                                   @QueryParam("pageSize") @DefaultValue("10") int size,
                                   @QueryParam("search") @DefaultValue("") String buscar) {
        System.out.print("*********************************\nAQUÍ VAMOS DE NUEVO\n********************************\n");

        try{
            String token = jwt.getRawToken();
            System.out.println("*********************************\nToken encontrado:\n********************************\n" + token);
            return cliente.getProductos(page, size, buscar, "Bearer " + token);
        } catch (Exception ex){
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @GET
    @Path("prueba")
    public Response getProducts(@QueryParam("currentPage") @DefaultValue("0") int page,
                                @QueryParam("pageSize") @DefaultValue("10") int size,
                                @QueryParam("search") @DefaultValue("") String buscar) throws IOException {

        List<RecompensaProductoDTO> productoDTOS = productoClientService.getProductos(size, page, buscar);
        return Response.status(Response.Status.OK).entity(productoDTOS).build();
    }
}
