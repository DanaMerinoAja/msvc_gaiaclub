package org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.miembro;

import java.time.LocalDate;

public class MiembroGetDTO {
    private Long idMiembro;
    private LocalDate fechaRegistro;
    private String nombresCompletos;
    private String telefono;
    private String correo;
    private Long clienteId;
    private long puntosDisponibles;
    private long puntosCercaVencer;
    private String dni;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Long getIdMiembro() {
        return idMiembro;
    }

    public void setIdMiembro(Long idMiembro) {
        this.idMiembro = idMiembro;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getNombresCompletos() {
        return nombresCompletos;
    }

    public void setNombresCompletos(String nombresCompletos) {
        this.nombresCompletos = nombresCompletos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public long getPuntosDisponibles() {
        return puntosDisponibles;
    }

    public void setPuntosDisponibles(long puntosDisponibles) {
        this.puntosDisponibles = puntosDisponibles;
    }

    public long getPuntosCercaVencer() {
        return puntosCercaVencer;
    }

    public void setPuntosCercaVencer(long puntosCercaVencer) {
        this.puntosCercaVencer = puntosCercaVencer;
    }
}
