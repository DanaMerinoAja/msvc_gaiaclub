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
    @Column(name = "cj_cliente", nullable = false)
    private long idCliente;

    @Column(name = "cj_fecha", nullable = false)
    private LocalDate fecha;

    @JoinColumn(name = "cj_recompensa", referencedColumnName = "rec_id")
    @ManyToOne
    private RecompensaEntity recompensa;

    @Column(name = "cj_puntosusados", nullable = false)
    private int puntosUsados;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dcjCanjePadre", fetch = FetchType.LAZY)
//    private List<DetalleCanjeEntity> detalleCanjes;

    public CanjeEntity() {
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

    public int getPuntosUsados() {
        return puntosUsados;
    }

    public void setPuntosUsados(int puntosUsados) {
        this.puntosUsados = puntosUsados;
    }

    //    public List<DetalleCanjeEntity> getDetalleCanjes() {
//        return detalleCanjes;
//    }
//
//    public void setDetalleCanjes(List<DetalleCanjeEntity> detalleCanjes) {
//        this.detalleCanjes = detalleCanjes;
//    }
}
