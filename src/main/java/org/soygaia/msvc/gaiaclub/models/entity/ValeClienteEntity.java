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
    @JoinColumn(name = "vc_vale", nullable = false, referencedColumnName = "v_id")
    private ValeEntity vale;
    @JoinColumn(name = "v_miembro", referencedColumnName = "mc_id", nullable = false)
    @ManyToOne
    private MiembroClubEntity miembroClub;
    @Column(name = "v_fechacaducidad", nullable = false)
    private LocalDate fechaCaducidad;
}
