package org.soygaia.msvc.gaiaclub.models.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "t_valescliente")
public class ValeClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, name = "vc_id")
    private Long id;
    @JoinColumn(name = "vc_vale", nullable = false, referencedColumnName = "rec_id")
    @ManyToOne
    private RecompensaEntity vale;
    @JoinColumn(name = "v_miembro", referencedColumnName = "mc_id", nullable = false)
    @ManyToOne
    private MiembroClubEntity miembroClub;
    @Column(name = "v_fechacaducidad", nullable = false)
    private LocalDate fechaCaducidad;
    //Canjeado, Vigente, Vencido
    @Enumerated(EnumType.STRING)
    @Column(name = "v_estado", nullable = false)
    private EstadoVale estado;

    public EstadoVale getEstado() {
        return estado;
    }

    public void setEstado(EstadoVale estado) {
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RecompensaEntity getVale() {
        return vale;
    }

    public void setVale(RecompensaEntity vale) {
        this.vale = vale;
    }

    public MiembroClubEntity getMiembroClub() {
        return miembroClub;
    }

    public void setMiembroClub(MiembroClubEntity miembroClub) {
        this.miembroClub = miembroClub;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public enum EstadoVale {
        VIGENTE, CADUCADO, CANJEADO
    }
}
