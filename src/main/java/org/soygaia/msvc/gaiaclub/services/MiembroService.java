package org.soygaia.msvc.gaiaclub.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.soygaia.msvc.gaiaclub.models.dtos.MiembroRegistroDTO;
import org.soygaia.msvc.gaiaclub.models.entity.MiembroClubEntity;
import org.soygaia.msvc.gaiaclub.repositories.MiembroRepository;

import java.time.LocalDate;
import java.util.Optional;


@ApplicationScoped
@Transactional
public class MiembroService {

    @Inject
    MiembroRepository miembroRepository;
    @Inject
    PuntosService puntosService;
    @PersistenceContext
    EntityManager entityManager;

    public MiembroClubEntity registrarMiembro(MiembroRegistroDTO miembro){

        Optional<MiembroClubEntity> opMiembro = obtenerMiembro(miembro.getClienteId(), miembro.getCorreo());

        if(opMiembro.isEmpty()){
            MiembroClubEntity miembroClubEntity = new MiembroClubEntity();
            miembroClubEntity.setClienteId(miembro.getClienteId());
            miembroClubEntity.setNombresCompletos(miembro.getNombresCompletos());
            miembroClubEntity.setTelefono(miembro.getTelefono());
            miembroClubEntity.setCorreo(miembro.getCorreo());
            miembroClubEntity.setFechaRegistro(LocalDate.now());

            miembroRepository.persist(miembroClubEntity);
            puntosService.registrarPuntosNuevoMiembro(miembroClubEntity);

            return miembroClubEntity;
        }
        return opMiembro.get();
    }

    public Optional<MiembroClubEntity> obtenerMiembro(Long idCliente, String correo){
        Optional<MiembroClubEntity> opMiembro = miembroRepository.find("SELECT m FROM MiembroClubEntity m WHERE m.clienteId= ?1 OR m.correo = ?2",
                idCliente,
                        correo).singleResultOptional();
        return opMiembro;
    }

}
