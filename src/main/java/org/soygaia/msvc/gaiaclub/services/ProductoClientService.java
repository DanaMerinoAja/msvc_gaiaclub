package org.soygaia.msvc.gaiaclub.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.json.JSONObject;
import org.soygaia.msvc.gaiaclub.config.properties.Constantes;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.productos.DataWrapper;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.productos.ListItem;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.productos.Producto;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.productos.RecompensaProductoDTO;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProductoClientService {
    @Inject
    JsonWebToken jwt;
    public List<RecompensaProductoDTO> getProductos(int size, int page, String search) throws IOException {
        String token = "Bearer "+ jwt.getRawToken();
        // ?pageSize=&currentPage=&search=&orderBy=

        //System.out.println("*********************************\nToken  en el service:\n********************************\n" + token);

        CloseableHttpClient httpClient = HttpClientBuilder.create().disableContentCompression().build();

        HttpGet httpGet = new HttpGet(Constantes.apiProductos+"?pageSize="+size+"&?currentPage=&"+page+"?search="+search);
        httpGet.addHeader("content-type", "application/json");
        httpGet.addHeader("Authorization", token);

        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);

            //System.out.println(httpClient.toString());
            HttpEntity entity = response.getEntity();

            String responseBody = EntityUtils.toString(entity, "UTF-8");
            //System.out.println("JSON obtenido: "+ responseBody.toString());

            response.close();

            ObjectMapper mapper = new ObjectMapper();

            JSONObject jsonObject= new JSONObject(responseBody);
            if(jsonObject.getString("transfer_status").equals("200")) {
                DataWrapper data = mapper.readValue(jsonObject.getJSONObject("data").toString(), DataWrapper.class);
                return procesarProductos(data);
            }

        } catch (Exception ex) {
            System.out.println("Excepción atrapada en ProductClientService: " + ex.getMessage() + "\n"+ex.getLocalizedMessage()+"\n");

        }finally {
            httpClient.close();
        }
        return null;
    }

    public List<RecompensaProductoDTO> procesarProductos(DataWrapper dataWrapper) throws Exception {

        List<ListItem> items = dataWrapper.getListItems();
        if (items == null || items.isEmpty()) {
            return java.util.Collections.emptyList();
        }

        // 4) Mapea cada item a tu RecompensaProductoDTO llenando solo lo necesario
        return items.stream().map(it -> {
            Producto prodApi = it.getPvtProducto();
            if (prodApi == null) return null;

            RecompensaProductoDTO productoDTO = new RecompensaProductoDTO();
            // id del producto (equivale a ProductoDTO.id)
            productoDTO.setRecProducto(prodApi.getPrdId());

            // nombre -> el mismo para la recompensa
            productoDTO.setNombreProducto(prodApi.getPrdNombre());
            productoDTO.setNombreRecompensa(prodApi.getPrdNombre());

            // precio -> pvtPreciomaximo
            if (it.getPvtPreciomaximo() != null) {
                productoDTO.setPrecioProducto(BigDecimal.valueOf(it.getPvtPreciomaximo()));
            }

            // slug (ProductoDTO.slug)
            productoDTO.setSlugProducto(prodApi.getPrdSlug());

            // abreviatura de presentación (ProductoDTO.abreviaturaUnidadMedidaPres)
            if (prodApi.getPrdPresentacion() != null) {
                productoDTO.setAbreviaturaUnidadMedidaPres(prodApi.getPrdPresentacion().getPpdAbreviatura());
                productoDTO.setPresentacion(prodApi.getPrdPresentacion().getPpdNombre()); // ProductoDTO.presentacion
            }

            // sku (ProductoDTO.sku)
            productoDTO.setSku(prodApi.getPrdSku());

            // marca (ProductoDTO.marca)
            if (prodApi.getPrdMarca() != null) {
                productoDTO.setMarca(prodApi.getPrdMarca().getMrcNombre());
            }

            productoDTO.setDescripcion(prodApi.getPrdNombre()
                    + " " + prodApi.getPrdMarca().getMrcNombre()
                    + " " + prodApi.getPrdCantidadmedida()
                    + prodApi.getPrdUndmedpresentacion().getUmdAbreviatura()
                    + " " + prodApi.getPrdUnidadmedida().getUmdAbreviatura());

            // cantidadMedida (ProductoDTO.cantidadMedida)
            productoDTO.setCantidadMedida(prodApi.getPrdCantidadmedida());

            // unidadMedidaVenta (ProductoDTO.unidadMedidaVenta) -> abreviatura de prdUnidadmedida (UND, etc.)
            if (prodApi.getPrdUnidadmedida() != null) {
                productoDTO.setUnidadMedidaVenta(prodApi.getPrdUnidadmedida().getUmdAbreviatura());
            }

            // imagenUrl (ProductoDTO.imagenUrl) -> lista de URLs (si es null, devuelve array vacío)
            String[] imagenes = (prodApi.getLstImagenproducto() == null)
                    ? new String[0]
                    : prodApi.getLstImagenproducto().stream()
                    .map(img -> img != null ? img.getUrl() : null)
                    .filter(java.util.Objects::nonNull)
                    .toArray(String[]::new);
            productoDTO.setImagenUrl(imagenes);

            return productoDTO;
        }).filter(java.util.Objects::nonNull).collect(Collectors.toList());
    }
}
