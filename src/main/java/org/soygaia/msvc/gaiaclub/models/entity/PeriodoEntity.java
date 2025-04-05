package org.soygaia.msvc.gaiaclub.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private LocalDate fechaInicio;
    @Column(name = "per_fecha_fin")
    private LocalDate fechaFin;
    @Column(name = "per_descripcion")
    private String descripcion;
    @Column(name = "per_nombre")
    private String nombre;
    @Column(name = "per_valor_punto")
    private double valorPunto;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "periodo", fetch = FetchType.LAZY)
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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
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

    public double getValorPunto() {
        return valorPunto;
    }

    public void setValorPunto(double valorPunto) {
        this.valorPunto = valorPunto;
    }
}
