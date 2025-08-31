package org.soygaia.msvc.gaiaclub.models.dtos.admin.productos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UnidadMedida {
    private Long umdId;
    private String umdNombre;       // "UNIDAD", "GRAMOS"
    private String umdAbreviatura;  // "UND", "GR"
    // getters y setters
    public Long getUmdId() { return umdId; }
    public void setUmdId(Long umdId) { this.umdId = umdId; }
    public String getUmdNombre() { return umdNombre; }
    public void setUmdNombre(String umdNombre) { this.umdNombre = umdNombre; }
    public String getUmdAbreviatura() { return umdAbreviatura; }
    public void setUmdAbreviatura(String umdAbreviatura) { this.umdAbreviatura = umdAbreviatura; }
}
