package org.soygaia.msvc.gaiaclub.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "t_detalles_canje")
public class DetalleCanjeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetCanje;

    @Column(name = "dcj_puntos")
    private int puntosDetCanje;

    @JoinColumn(name = "dcj_recompensa" , referencedColumnName = "rec_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RecompensaEntity dcjRecompensa;

    @Column(name = "cj_cantidadrec", nullable = false)
    private int cantidadRecompensa;

    @JoinColumn(name = "dcj_canjePadre" , referencedColumnName = "cj_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CanjeEntity dcjCanjePadre;

    public DetalleCanjeEntity() {
    }

    public Long getIdDetCanje() {
        return idDetCanje;
    }

    public void setIdDetCanje(Long idDetCanje) {
        this.idDetCanje = idDetCanje;
    }

    public int getPuntosDetCanje() {
        return puntosDetCanje;
    }

    public void setPuntosDetCanje(int puntosDetCanje) {
        this.puntosDetCanje = puntosDetCanje;
    }

    public RecompensaEntity getDcjRecompensa() {
        return dcjRecompensa;
    }

    public void setDcjRecompensa(RecompensaEntity dcjRecompensa) {
        this.dcjRecompensa = dcjRecompensa;
    }

    public int getCantidadRecompensa() {
        return cantidadRecompensa;
    }

    public void setCantidadRecompensa(int cantidadRecompensa) {
        this.cantidadRecompensa = cantidadRecompensa;
    }

    public CanjeEntity getDcjCanjePadre() {
        return dcjCanjePadre;
    }

    public void setDcjCanjePadre(CanjeEntity dcjCanjePadre) {
        this.dcjCanjePadre = dcjCanjePadre;
    }
}
