package org.soygaia.msvc.gaiaclub.models.dtos;

public class RecompensaProductoDTO {

    private Long recId;
    private Double aporteSoles;
    private String descripcion;
    private Long recProducto;
    private String nombreRecompensa;
    private int puntosRequeridos;
    private int stock;
    private Long periodoId;
    private String nombreProducto;
    private String slugProducto;
    private String abreviaturaUnidadMedida;
    private String nombreUnidadMedida;
    private String [] imagenUrl;

    // Constructor


    public RecompensaProductoDTO(Long recId, Double aporteSoles, String descripcion, Long recProducto, String nombreRecompensa, int puntosRequeridos, int stock, Long periodoId, String nombreProducto, String slugProducto, String abreviaturaUnidadMedida, String nombreUnidadMedida, String imagenUrls) {
        this.recId = recId;
        this.aporteSoles = aporteSoles;
        this.descripcion = descripcion;
        this.recProducto = recProducto;
        this.nombreRecompensa = nombreRecompensa;
        this.puntosRequeridos = puntosRequeridos;
        this.stock = stock;
        this.periodoId = periodoId;
        this.nombreProducto = nombreProducto;
        this.slugProducto = slugProducto;
        this.abreviaturaUnidadMedida = abreviaturaUnidadMedida;
        this.nombreUnidadMedida = nombreUnidadMedida;
        if (imagenUrls != null && !imagenUrls.isEmpty()) {
            this.imagenUrl = imagenUrls.split(",");
        }

    }

    public Long getRecId() { return recId; }
    public void setRecId(Long recId) { this.recId = recId; }

    public Double getAporteSoles() { return aporteSoles; }
    public void setAporteSoles(Double aporteSoles) { this.aporteSoles = aporteSoles; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Long getRecProducto() { return recProducto; }
    public void setRecProducto(Long recProducto) { this.recProducto = recProducto; }

    public String getNombreRecompensa() { return nombreRecompensa; }
    public void setNombreRecompensa(String nombreRecompensa) { this.nombreRecompensa = nombreRecompensa; }

    public int getPuntosRequeridos() { return puntosRequeridos; }
    public void setPuntosRequeridos(int puntosRequeridos) { this.puntosRequeridos = puntosRequeridos; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public Long getPeriodoId() { return periodoId; }
    public void setPeriodoId(Long periodoId) { this.periodoId = periodoId; }

    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }

    public String getSlugProducto() { return slugProducto; }
    public void setSlugProducto(String slugProducto) { this.slugProducto = slugProducto; }

    public String getAbreviaturaUnidadMedida() { return abreviaturaUnidadMedida; }
    public void setAbreviaturaUnidadMedida(String abreviaturaUnidadMedida) { this.abreviaturaUnidadMedida = abreviaturaUnidadMedida; }

    public String getNombreUnidadMedida() { return nombreUnidadMedida; }
    public void setNombreUnidadMedida(String nombreUnidadMedida) { this.nombreUnidadMedida = nombreUnidadMedida; }

    public String[] getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl.split(","); }
}
