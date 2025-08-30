package org.soygaia.msvc.gaiaclub.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.soygaia.msvc.gaiaclub.clients.ProductoResourceClient;

@Path("/precio-venta")
@Produces(MediaType.APPLICATION_JSON)
public class ProductoResource {

    @Inject
    @RestClient
    ProductoResourceClient cliente;

    @GET
    public Response consultar(@QueryParam("currentPage") @DefaultValue("0") int page,
                              @QueryParam("pageSize") @DefaultValue("10") int size,
                              @QueryParam("search") @DefaultValue("") String buscar) {
        //Aquí ya está validado el token entrante y el filtro lo propagará
        Response r = cliente.getProductos(page, size, buscar);
        return Response.status(r.getStatus()).entity(r.readEntity(String.class)).build();
    }
}
