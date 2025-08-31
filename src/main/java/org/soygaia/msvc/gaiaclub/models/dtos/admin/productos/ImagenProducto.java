package org.soygaia.msvc.gaiaclub.models.dtos.admin.productos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImagenProducto {
    // El API no mostró el campo exacto; pon el tuyo real (por ejemplo "url" o "imagenUrl")
    private String url;

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
}
