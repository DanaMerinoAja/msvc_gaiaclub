package org.soygaia.msvc.gaiaclub.clients;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.soygaia.msvc.gaiaclub.providers.BearerTokenFilter;


@Path("/api-sucursal-prod")
@RegisterRestClient(configKey = "sucursal-api")
@RegisterProvider(BearerTokenFilter.class)
public interface ProductoResourceClient {
    //http://192.168.10.62:5064/api-sucursal-prod/sucursal-producto/precio-venta
    //?pageSize=&currentPage=&search=&orderBy=
    @GET
    @Path("/sucursal-producto/precio-venta")
    Response getProductos(@QueryParam("currentPage") @DefaultValue("0") int page,
                                   @QueryParam("pageSize") @DefaultValue("10") int size,
                          @QueryParam("search") @DefaultValue("") String buscar);
}
