package org.soygaia.msvc.gaiaclub.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.smallrye.common.constraint.NotNull;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "t_recompensas")
public class RecompensaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rec_id")
    private Long id;

    @Column(name = "rec_producto")
    private Long idProducto;

    @Column(name = "rec_nombre")
    private String nombre;

    @NotNull
    @Column(name = "rec_puntosreq")
    private int puntosRequeridos;

    @Column(name = "rec_aportesoles")
    private double aporteSoles;

    @Column(name = "rec_descripcion")
    private String descripcion;

    @Column(name = "rec_stock")
    private int stock;

    @JsonIgnore
    @JoinColumn(name = "rec_periodo", referencedColumnName = "per_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private PeriodoEntity periodo;

    public RecompensaEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntosRequeridos() {
        return puntosRequeridos;
    }

    public void setPuntosRequeridos(int puntosRequeridos) {
        this.puntosRequeridos = puntosRequeridos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public PeriodoEntity getPeriodo() {
        return periodo;
    }

    public void setPeriodo(PeriodoEntity periodo) {
        this.periodo = periodo;
    }

    public double getAporteSoles() {
        return aporteSoles;
    }

    public void setAporteSoles(double aporteSoles) {
        this.aporteSoles = aporteSoles;
    }
}
