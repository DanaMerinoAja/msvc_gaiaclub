package org.soygaia.msvc.gaiaclub.models.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="t_canjes")
public class CanjeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cj_id")
    private Long id;

    //sospechoso
    @Column(name = "cj_cliente")
    private long id_cliente;

    @Column(name = "cj_fecha")
    private LocalDate fecha;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dcjCanje", fetch = FetchType.LAZY)
    private List<DetalleCanjeEntity> detalleCanjes;

    public CanjeEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public List<DetalleCanjeEntity> getDetalleCanjes() {
        return detalleCanjes;
    }

    public void setDetalleCanjes(List<DetalleCanjeEntity> detalleCanjes) {
        this.detalleCanjes = detalleCanjes;
    }
}
