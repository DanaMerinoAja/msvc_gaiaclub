package org.soygaia.msvc.gaiaclub.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.miembro.MiembroGetDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.miembro.MiembroRegistroDTO;
import org.soygaia.msvc.gaiaclub.models.entity.MiembroClubEntity;
import org.soygaia.msvc.gaiaclub.repositories.MiembroRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

    @ConfigProperty(name = "gaia.puntos.valor-bienvenida", defaultValue = "5")
    int bonificacionBienvenida;

    public List<MiembroGetDTO> listaMiembros(){
        List<MiembroClubEntity> list =  miembroRepository.findAll().list();

        for(MiembroClubEntity m : list){

        }

        return new ArrayList<>();
    }

    public MiembroGetDTO registrarMiembro(MiembroRegistroDTO miembro){

        Optional<MiembroClubEntity> opMiembro = miembroRepository.find("SELECT m FROM MiembroClubEntity m WHERE m.clienteId= ?1 OR m.correo = ?2",
                miembro.getClienteId(),
                miembro.getCorreo()).singleResultOptional();


        MiembroClubEntity miembroClubEntity = new MiembroClubEntity();
        MiembroGetDTO miembroGetDTO = new MiembroGetDTO();

        if(opMiembro.isEmpty()){
            miembroClubEntity.setClienteId(miembro.getClienteId());
            miembroClubEntity.setNombresCompletos(miembro.getNombresCompletos());
            miembroClubEntity.setTelefono(miembro.getTelefono());
            miembroClubEntity.setDni(miembro.getDni());
            miembroClubEntity.setCorreo(miembro.getCorreo());
            miembroClubEntity.setFechaRegistro(LocalDate.now());

            miembroRepository.persist(miembroClubEntity);
            puntosService.registrarPuntosNuevoMiembro(miembroClubEntity);

            miembroGetDTO.setIdMiembro(miembroClubEntity.getIdMiembro());
            miembroGetDTO.setCorreo(miembroClubEntity.getCorreo());
            miembroGetDTO.setDni(miembroClubEntity.getDni());
            miembroGetDTO.setTelefono(miembroClubEntity.getTelefono());
            miembroGetDTO.setNombresCompletos(miembroClubEntity.getNombresCompletos());
            miembroGetDTO.setFechaRegistro(miembroClubEntity.getFechaRegistro());
            miembroGetDTO.setClienteId(miembroClubEntity.getClienteId());
            miembroGetDTO.setPuntosCercaVencer(0);
            miembroGetDTO.setPuntosDisponibles(bonificacionBienvenida);
        } else {
            miembroClubEntity = opMiembro.get();

            miembroGetDTO.setIdMiembro(miembroClubEntity.getIdMiembro());
            miembroGetDTO.setCorreo(miembroClubEntity.getCorreo());
            miembroGetDTO.setTelefono(miembroClubEntity.getTelefono());
            miembroGetDTO.setNombresCompletos(miembroClubEntity.getNombresCompletos());
            miembroGetDTO.setFechaRegistro(miembroClubEntity.getFechaRegistro());
            miembroGetDTO.setClienteId(miembroClubEntity.getClienteId());
            miembroGetDTO.setPuntosCercaVencer(puntosService.getTotalPuntosCercanosVencerPorCliente(miembroClubEntity.getIdMiembro()));
            miembroGetDTO.setPuntosDisponibles(puntosService.getTotalPuntosDisponiblesPorCliente(miembroClubEntity.getIdMiembro()));
        }
        return miembroGetDTO;
    }

    public Optional<MiembroGetDTO> obtenerMiembro(Long idCliente, String correo){
        Optional<MiembroClubEntity> opMiembro = miembroRepository.find("SELECT m FROM MiembroClubEntity m WHERE m.clienteId= ?1 OR m.correo = ?2",
                idCliente,
                        correo).singleResultOptional();
        if(opMiembro.isPresent()){
            MiembroClubEntity miembroClub = opMiembro.get();

            MiembroGetDTO miembroGetDTO = new MiembroGetDTO();

            miembroGetDTO.setIdMiembro(miembroClub.getIdMiembro());
            miembroGetDTO.setCorreo(miembroClub.getCorreo());
            miembroGetDTO.setDni(miembroClub.getDni());
            miembroGetDTO.setTelefono(miembroClub.getTelefono());
            miembroGetDTO.setNombresCompletos(miembroClub.getNombresCompletos());
            miembroGetDTO.setFechaRegistro(miembroClub.getFechaRegistro());
            miembroGetDTO.setClienteId(miembroClub.getClienteId());
            Long puntosPorVencer = puntosService.getTotalPuntosCercanosVencerPorCliente(miembroGetDTO.getIdMiembro());
            Long puntosDisponibles = puntosService.getTotalPuntosDisponiblesPorCliente(miembroGetDTO.getIdMiembro());
            miembroGetDTO.setPuntosCercaVencer(puntosPorVencer != null ? puntosPorVencer:0);
            miembroGetDTO.setPuntosDisponibles(puntosDisponibles !=null ? puntosDisponibles:0);
            return Optional.of(miembroGetDTO);
        }
        return Optional.empty();
    }

}
