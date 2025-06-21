package org.soygaia.msvc.gaiaclub.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.smallrye.common.constraint.NotNull;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "t_recompensas", schema = "gaiaadm")
public class RecompensaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rec_id", nullable = false)
    private Long recId;

    @Column(name = "rec_nombre")
    private String nombre;

    @Column(name = "rec_producto")
    private Long productoId;

    @Column(name = "rec_descripcion")
    private String descripcion;

    @Column(name = "rec_puntosreq")
    private Integer puntosRequeridos;

    @Column(name = "rec_aportesoles")
    private Double aporteSoles;

    @Column(name = "rec_stock")
    private Integer stock;

    @JoinColumn(name = "rec_periodo", referencedColumnName = "per_id", nullable = false)
    @ManyToOne
    private PeriodoEntity periodo;

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

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
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

    public Double getAporteSoles() {
        return aporteSoles;
    }

    public void setAporteSoles(Double aporteSoles) {
        this.aporteSoles = aporteSoles;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public PeriodoEntity getPeriodo() {
        return periodo;
    }

    public void setPeriodo(PeriodoEntity periodo) {
        this.periodo = periodo;
    }
}

