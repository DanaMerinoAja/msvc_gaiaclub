package org.soygaia.msvc.gaiaclub.models.entity;

import jakarta.persistence.*;

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
    @Column(name = "boni_cliente")
    private Long idCliente;

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

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
}
