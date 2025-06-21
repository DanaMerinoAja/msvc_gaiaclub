package org.soygaia.msvc.gaiaclub.models.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "t_vales_cliente")
public class ValeClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vc_id")
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "vc_miembro", referencedColumnName = "mc_id")
    private MiembroClubEntity miembro;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "vc_vale_periodo", referencedColumnName = "vp_id")
    private ValePeriodoEntity valePeriodo;

    @Column(name = "vc_fecha_canje", nullable = false)
    private LocalDate fechaCanje;

    @Column(name = "vc_fecha_caducidad", nullable = false)
    private LocalDate fechaCaducidad;

    @Column(name = "vc_valor_puntos", nullable = false)
    private Integer valorPuntos; // puntos usados en el momento del canje

    @Enumerated(EnumType.STRING)
    @Column(name = "vc_estado", nullable = false)
    private EstadoValeCliente estado;

    public enum EstadoValeCliente {
        VIGENTE,
        USADO,
        CADUCADO
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MiembroClubEntity getMiembro() {
        return miembro;
    }

    public void setMiembro(MiembroClubEntity miembro) {
        this.miembro = miembro;
    }

    public ValePeriodoEntity getValePeriodo() {
        return valePeriodo;
    }

    public void setValePeriodo(ValePeriodoEntity valePeriodo) {
        this.valePeriodo = valePeriodo;
    }

    public LocalDate getFechaCanje() {
        return fechaCanje;
    }

    public void setFechaCanje(LocalDate fechaCanje) {
        this.fechaCanje = fechaCanje;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public Integer getValorPuntos() {
        return valorPuntos;
    }

    public void setValorPuntos(Integer valorPuntos) {
        this.valorPuntos = valorPuntos;
    }

    public EstadoValeCliente getEstado() {
        return estado;
    }

    public void setEstado(EstadoValeCliente estado) {
        this.estado = estado;
    }
}