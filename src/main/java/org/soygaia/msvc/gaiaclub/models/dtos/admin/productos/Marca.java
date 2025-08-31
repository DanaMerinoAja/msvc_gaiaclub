package org.soygaia.msvc.gaiaclub.models.dtos.admin.productos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Marca {
    private Long mrcId;
    private String mrcNombre;
    // getters y setters
    public Long getMrcId() { return mrcId; }
    public void setMrcId(Long mrcId) { this.mrcId = mrcId; }
    public String getMrcNombre() { return mrcNombre; }
    public void setMrcNombre(String mrcNombre) { this.mrcNombre = mrcNombre; }
}
