package org.soygaia.msvc.gaiaclub.models.dtos.admin.productos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Presentacion {
    private Long ppdId;
    private String ppdAbreviatura; // "BOL ZIPL"
    private String ppdNombre;      // "BOLSA ZIPLOCK"
    // getters y setters
    public Long getPpdId() { return ppdId; }
    public void setPpdId(Long ppdId) { this.ppdId = ppdId; }
    public String getPpdAbreviatura() { return ppdAbreviatura; }
    public void setPpdAbreviatura(String ppdAbreviatura) { this.ppdAbreviatura = ppdAbreviatura; }
    public String getPpdNombre() { return ppdNombre; }
    public void setPpdNombre(String ppdNombre) { this.ppdNombre = ppdNombre; }
}
