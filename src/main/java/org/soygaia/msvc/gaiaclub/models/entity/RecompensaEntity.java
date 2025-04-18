package org.soygaia.msvc.gaiaclub.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.smallrye.common.constraint.NotNull;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "t_recompensas", schema = "gaiaadm")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "rec_tipo", discriminatorType = DiscriminatorType.STRING)
public abstract class RecompensaEntity {

    @Id
    @Column(name = "rec_id")
    private Long recId;

    @Column(name = "rec_nombre")
    private String nombre;

    @Column(name = "rec_descripcion")
    private String descripcion;

    @Column(name = "rec_puntosreq")
    private Integer puntosRequeridos;

    @Column(name = "rec_stock")
    private Integer stock;

    @Column(name = "rec_periodo")
    private Long periodoId;

    public Long getRecId() {
        return recId;
    }

    public void setRecId(Long recId) {
        this.recId = recId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPuntosRequeridos() {
        return puntosRequeridos;
    }

    public void setPuntosRequeridos(Integer puntosRequeridos) {
        this.puntosRequeridos = puntosRequeridos;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Long getPeriodoId() {
        return periodoId;
    }

    public void setPeriodoId(Long periodoId) {
        this.periodoId = periodoId;
    }
}

