package org.soygaia.msvc.gaiaclub.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JoinColumn(name = "cj_miembro", referencedColumnName = "mc_id")
    @ManyToOne
    private MiembroClubEntity miembro;

    @Column(name = "cj_fecha", nullable = false)
    private LocalDate fecha;

    @JoinColumn(name = "cj_periodo", referencedColumnName = "per_id")
    @ManyToOne
    private PeriodoEntity periodo;

    @Enumerated(EnumType.STRING)
    @Column(name = "cj_estado", nullable = false)
    private EstadoCanje estado;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dcjCanjePadre", fetch = FetchType.LAZY)
    private List<DetalleCanjeEntity> detallesCanje;

    public CanjeEntity() {
    }

    public List<DetalleCanjeEntity> getDetallesCanje() {
        return detallesCanje;
    }

    public void setDetallesCanje(List<DetalleCanjeEntity> detallesCanje) {
        this.detallesCanje = detallesCanje;
    }

    public PeriodoEntity getPeriodo() {
        return periodo;
    }

    public void setPeriodo(PeriodoEntity periodo) {
        this.periodo = periodo;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public EstadoCanje getEstado() {
        return estado;
    }

    public void setEstado(EstadoCanje estado) {
        this.estado = estado;
    }

    public enum EstadoCanje {
        POR_ENTREGAR, ENTREGADO, CANCELADO
    }
}
