package org.soygaia.msvc.gaiaclub.models.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "t_vales")
public class ValeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "val_id")
    private Long id;

    @Column(name = "val_nombre", nullable = false)
    private String nombre;

    @Column(name = "val_descripcion")
    private String descripcion;

    @Column(name = "val_valor_soles", nullable = false)
    private Double valorSoles;

    @Column(name = "val_puntos_requeridos", nullable = false)
    private Integer puntosRequeridos;

    @Column(name = "val_vigencia_dias", nullable = false)
    private Integer vigenciaDias;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getValorSoles() {
        return valorSoles;
    }

    public void setValorSoles(Double valorSoles) {
        this.valorSoles = valorSoles;
    }

    public Integer getPuntosRequeridos() {
        return puntosRequeridos;
    }

    public void setPuntosRequeridos(Integer puntosRequeridos) {
        this.puntosRequeridos = puntosRequeridos;
    }

    public Integer getVigenciaDias() {
        return vigenciaDias;
    }

    public void setVigenciaDias(Integer vigenciaDias) {
        this.vigenciaDias = vigenciaDias;
    }

}
