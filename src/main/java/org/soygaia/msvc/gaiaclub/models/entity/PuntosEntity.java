package org.soygaia.msvc.gaiaclub.models.entity;

import jakarta.persistence.*;

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
    private long idOrigen;

    //manejo de relación polimórfica
    @Column(name = "pt_tipo_origen")
    private String tipoOrigen;

    @Column(name = "pt_fechaemision")
    private LocalDate fechaEmision;
    @Column(name = "pt_fechacaducidad")
    private LocalDate fechaCaducidad;
    @Column(name = "pt_fechacanje")
    private LocalDate fechaCanje;

    @JoinColumn(name = "pt_cliente", referencedColumnName = "c_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private long idCliente;

    @Column(name = "pt_estado")
    private String estado;
    @Column(name = "pt_puntoscanjeados")
    private int puntosCanjeados;

    public int getTotalPuntos() {
        return totalPuntos;
    }

    public void setTotalPuntos(int totalPuntos) {
        this.totalPuntos = totalPuntos;
    }

    public long getIdOrigen() {
        return idOrigen;
    }

    public void setIdOrigen(long idOrigen) {
        this.idOrigen = idOrigen;
    }

    public String getTipoOrigen() {
        return tipoOrigen;
    }

    public void setTipoOrigen(String tipoOrigen) {
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

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getPuntosCanjeados() {
        return puntosCanjeados;
    }

    public void setPuntosCanjeados(int puntosCanjeados) {
        this.puntosCanjeados = puntosCanjeados;
    }
}
