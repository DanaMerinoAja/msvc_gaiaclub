package org.soygaia.msvc.gaiaclub.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "t_puntos")
public class PuntosEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pt_id")
    private Long id;

    @Column(name = "pt_total")
    private int totalPuntos;

    @Column(name = "pt_origen")
    private Long idOrigen;
    //manejo de relación polimórfica
    @Column(name = "pt_tipo_origen")
    private TipoOrigen tipoOrigen;
    @Column(name = "pt_fechaemision")
    private LocalDate fechaEmision;
    @Column(name = "pt_fechacaducidad")
    private LocalDate fechaCaducidad;
    @Column(name = "pt_fechacanje")
    private LocalDate fechaCanje;

    @JoinColumn(name = "pt_miembro", referencedColumnName = "mc_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MiembroClubEntity miembro;
    //VIGENTE, CADUCADO, CANJEADO
    @Enumerated(EnumType.STRING)
    @Column(name = "pt_estado")
    @NotNull
    private EstadoPuntos estado;

    @Column(name = "pt_puntoscanjeados")
    private int puntosCanjeados;

    @AssertTrue(message = "No se pueden canjear puntos en estado VIGENTE")
    public boolean isPuntosValidos() {
        return !"VIGENTE".equals(estado) || puntosCanjeados != 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTotalPuntos() {
        return totalPuntos;
    }

    public void setTotalPuntos(int totalPuntos) {
        this.totalPuntos = totalPuntos;
    }

    public Long getIdOrigen() {
        return idOrigen;
    }

    public void setIdOrigen(Long idOrigen) {
        this.idOrigen = idOrigen;
    }

    public TipoOrigen getTipoOrigen() {
        return tipoOrigen;
    }

    public void setTipoOrigen(TipoOrigen tipoOrigen) {
        this.tipoOrigen = tipoOrigen;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public LocalDate getFechaCanje() {
        return fechaCanje;
    }

    public void setFechaCanje(LocalDate fechaCanje) {
        this.fechaCanje = fechaCanje;
    }

    public MiembroClubEntity getMiembro() {
        return miembro;
    }

    public void setMiembro(MiembroClubEntity miembro) {
        this.miembro = miembro;
    }

    public EstadoPuntos getEstado() {
        return estado;
    }

    public void setEstado(EstadoPuntos estado) {
        this.estado = estado;
    }

    public int getPuntosCanjeados() {
        return puntosCanjeados;
    }

    public void setPuntosCanjeados(int puntosCanjeados) {
        this.puntosCanjeados = puntosCanjeados;
    }

    public enum EstadoPuntos {
        VIGENTE, CADUCADO, CANJEADO
    }

    public enum TipoOrigen {
        COMPRA, BONIFICACION, PROMOCION
    }

}
