package org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.canjes;

public class DetalleCanjeGet {
    private Long idRecompensa;
    private int cantidadRecompensa;
    private int puntosDetCanje;
    private String nombreRecompensa;
    private String[] imagenUrl;

    public Long getIdRecompensa() {
        return idRecompensa;
    }

    public void setIdRecompensa(Long idRecompensa) {
        this.idRecompensa = idRecompensa;
    }

    public int getCantidadRecompensa() {
        return cantidadRecompensa;
    }

    public void setCantidadRecompensa(int cantidadRecompensa) {
        this.cantidadRecompensa = cantidadRecompensa;
    }

    public int getPuntosDetCanje() {
        return puntosDetCanje;
    }

    public void setPuntosDetCanje(int puntosDetCanje) {
        this.puntosDetCanje = puntosDetCanje;
    }

    public String getNombreRecompensa() {
        return nombreRecompensa;
    }

    public void setNombreRecompensa(String nombreRecompensa) {
        this.nombreRecompensa = nombreRecompensa;
    }

    public String[] getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String[] imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
}
