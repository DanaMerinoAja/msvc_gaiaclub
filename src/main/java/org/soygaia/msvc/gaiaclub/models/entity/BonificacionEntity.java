package org.soygaia.msvc.gaiaclub.models.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Table(name = "t_bonificacion")
public class BonificacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boni_id")
    private Long id;
    @Column(name = "boni_descripcion")
    private String descripcion;
    @Column(name = "boni_nombre")
    private String nombre;
    @Column(name = "boni_puntos")
    private Integer puntos;
    @Column(name = "boni_creacion", nullable = false)
    private LocalDateTime fechaCreacion;
    @Column(name = "boni_modificacion")
    private LocalDateTime fechaModificacion;
    @ManyToOne
    @JoinColumn(name = "boni_origen")
    private BonificacionEntity bonificacionOrigen;
    @Column(name = "boni_activa", nullable = false)
    private boolean activa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDateTime fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public BonificacionEntity getBonificacionOrigen() {
        return bonificacionOrigen;
    }

    public void setBonificacionOrigen(BonificacionEntity bonificacionOrigen) {
        this.bonificacionOrigen = bonificacionOrigen;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
}
