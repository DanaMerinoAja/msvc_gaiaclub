package org.soygaia.msvc.gaiaclub.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Table(name = "t_miembroclub")
@Entity
public class MiembroClubEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "mc_id", nullable = false)
    private Long id;
    @Column(name = "mc_fecharegistro", nullable = false)
    private LocalDate fechaRegistro;
    @Column(name = "mc_nombrecompleto", nullable = false)
    private String nombresCompletos;
    @Column(name = "mc_telefono")
    private String telefono;
    @Column(name = "mc_correo")
    private String correo;
    @Column(name = "mc_cliente")
    private Long clienteId;
    @Column(name = "mc_dni")
    private String dni;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "miembro", fetch = FetchType.LAZY)
    private List<PuntosEntity> puntos;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "miembro", fetch = FetchType.LAZY)
    private List<CanjeEntity> canjes;

    public List<CanjeEntity> getCanjes() {
        return canjes;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setCanjes(List<CanjeEntity> canjes) {
        this.canjes = canjes;
    }

    public List<PuntosEntity> getPuntos() {
        return puntos;
    }

    public void setPuntos(List<PuntosEntity> puntos) {
        this.puntos = puntos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
