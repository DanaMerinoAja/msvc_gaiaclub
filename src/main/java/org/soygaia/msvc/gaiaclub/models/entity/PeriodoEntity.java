package org.soygaia.msvc.gaiaclub.models.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "t_periodo")
public class PeriodoEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "per_id")
    private Long id;

    @Column(name = "per_fecha_inicio")
    private LocalDate fecha_inicio;
    @Column(name = "per_fecha_fin")
    private LocalDate fecha_fin;
    @Column(name = "per_descripcion")
    private String descripcion;
    @Column(name = "per_valor_punto")
    private double valor_punto;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recIdPeriodo", fetch = FetchType.LAZY)
    private List<RecompensaEntity> recompensaList;

    public Long getId() {
        return id;
    }

    public List<RecompensaEntity> getRecompensaList() {
        return recompensaList;
    }

    public void setRecompensaList(List<RecompensaEntity> recompensaList) {
        this.recompensaList = recompensaList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getValor_punto() {
        return valor_punto;
    }

    public void setValor_punto(double valor_punto) {
        this.valor_punto = valor_punto;
    }
}
