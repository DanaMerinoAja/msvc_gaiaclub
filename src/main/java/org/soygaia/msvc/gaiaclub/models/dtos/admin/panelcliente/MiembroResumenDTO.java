package org.soygaia.msvc.gaiaclub.models.dtos.admin.panelcliente;

public class MiembroResumenDTO {
    private Long id;
    private String nombre;
    private String correo;
    private String dni;
    private String telefono;
    private Integer puntosActuales;
    private Integer puntosPorVencer; // dentro de X días, configurable

    public MiembroResumenDTO(Long id, String nombre, String correo, String dni, String telefono, Integer puntosActuales, Integer puntosPorVencer) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.dni = dni;
        this.telefono = telefono;
        this.puntosActuales = puntosActuales;
        this.puntosPorVencer = puntosPorVencer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getPuntosActuales() {
        return puntosActuales;
    }

    public void setPuntosActuales(Integer puntosActuales) {
        this.puntosActuales = puntosActuales;
    }

    public Integer getPuntosPorVencer() {
        return puntosPorVencer;
    }

    public void setPuntosPorVencer(Integer puntosPorVencer) {
        this.puntosPorVencer = puntosPorVencer;
    }
}
