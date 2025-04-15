package org.soygaia.msvc.gaiaclub.models.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="t_canjes")
public class CanjeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cj_id")
    private Long id;

    //sospechoso
    @Column(name = "cj_cliente", nullable = false)
    private long idCliente;

    @Column(name = "cj_fecha", nullable = false)
    private LocalDate fecha;

    @JoinColumn(name = "cj_recompensa", referencedColumnName = "rec_id")
    @ManyToOne
    private RecompensaEntity recompensa;

    @JoinColumn(name = "cj_periodo", referencedColumnName = "per_id")
    @ManyToOne
    private PeriodoEntity periodo;

    @Column(name = "cj_puntoscanjeados", nullable = false)
    private int puntosCanjeados;

    @Column(name = "cj_cantidadrec", nullable = false)
    private int cantidadRecompensa;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dcjCanjePadre", fetch = FetchType.LAZY)
//    private List<DetalleCanjeEntity> detalleCanjes;

    public CanjeEntity() {
    }

    public int getCantidadRecompensa() {
        return cantidadRecompensa;
    }

    public void setCantidadRecompensa(int cantidadRecompensa) {
        this.cantidadRecompensa = cantidadRecompensa;
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

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public RecompensaEntity getRecompensa() {
        return recompensa;
    }

    public void setRecompensa(RecompensaEntity recompensa) {
        this.recompensa = recompensa;
    }

    public int getPuntosCanjeados() {
        return puntosCanjeados;
    }

    public void setPuntosCanjeados(int puntosCanjeados) {
        this.puntosCanjeados = puntosCanjeados;
    }

    //    public List<DetalleCanjeEntity> getDetalleCanjes() {
//        return detalleCanjes;
//    }
//
//    public void setDetalleCanjes(List<DetalleCanjeEntity> detalleCanjes) {
//        this.detalleCanjes = detalleCanjes;
//    }
}
