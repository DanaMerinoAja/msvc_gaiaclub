package org.soygaia.msvc.gaiaclub.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "t_vales_periodo")
public class ValePeriodoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vp_id")
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "vp_vale", referencedColumnName = "val_id")
    private ValeEntity vale;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "vp_periodo", referencedColumnName = "per_id")
    private PeriodoEntity periodo;

    @Column(name = "vp_activo", nullable = false)
    private Boolean activo = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ValeEntity getVale() {
        return vale;
    }

    public void setVale(ValeEntity vale) {
        this.vale = vale;
    }

    public PeriodoEntity getPeriodo() {
        return periodo;
    }

    public void setPeriodo(PeriodoEntity periodo) {
        this.periodo = periodo;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}