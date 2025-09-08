package org.soygaia.msvc.gaiaclub.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.soygaia.msvc.gaiaclub.config.properties.Constantes;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.miembro.MiembroGetDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.miembro.MiembroRegistroDTO;
import org.soygaia.msvc.gaiaclub.models.entity.MiembroClubEntity;
import org.soygaia.msvc.gaiaclub.repositories.MiembroRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@ApplicationScoped
@Transactional
public class MiembroService {

    @Inject
    MiembroRepository miembroRepository;
    @Inject
    PuntosService puntosService;


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

            miembroGetDTO.setIdMiembro(miembroClubEntity.getId());
            miembroGetDTO.setCorreo(miembroClubEntity.getCorreo());
            miembroGetDTO.setDni(miembroClubEntity.getDni());
            miembroGetDTO.setTelefono(miembroClubEntity.getTelefono());
            miembroGetDTO.setNombresCompletos(miembroClubEntity.getNombresCompletos());
            miembroGetDTO.setFechaRegistro(miembroClubEntity.getFechaRegistro());
            miembroGetDTO.setClienteId(miembroClubEntity.getClienteId());
            miembroGetDTO.setPuntosCercaVencer(0);
            miembroGetDTO.setPuntosDisponibles(Constantes.bonificacionBienvenida);
        } else {
            miembroClubEntity = opMiembro.get();

            miembroGetDTO.setIdMiembro(miembroClubEntity.getId());
            miembroGetDTO.setCorreo(miembroClubEntity.getCorreo());
            miembroGetDTO.setTelefono(miembroClubEntity.getTelefono());
            miembroGetDTO.setNombresCompletos(miembroClubEntity.getNombresCompletos());
            miembroGetDTO.setFechaRegistro(miembroClubEntity.getFechaRegistro());
            miembroGetDTO.setClienteId(miembroClubEntity.getClienteId());
            miembroGetDTO.setPuntosCercaVencer(puntosService.getTotalPuntosCercanosVencerPorCliente(miembroClubEntity.getId()));
            miembroGetDTO.setPuntosDisponibles(puntosService.getTotalPuntosDisponiblesPorCliente(miembroClubEntity.getId()));
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

            miembroGetDTO.setIdMiembro(miembroClub.getId());
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

    public List<MiembroGetDTO> obtenerMiembros(){
        List<MiembroClubEntity> listMiembros = miembroRepository.listAll();
        return listMiembros.stream().map(miembroClub -> {
            MiembroGetDTO miembroGetDTO = new MiembroGetDTO();
            miembroGetDTO.setIdMiembro(miembroClub.getId());
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
            return miembroGetDTO;
        }).collect(Collectors.toList());
    }

    public MiembroGetDTO editarMiembro(MiembroGetDTO miembro){
        MiembroClubEntity miembroClub = miembroRepository.findById(miembro.getIdMiembro());
        miembroClub.setNombresCompletos(miembro.getNombresCompletos());
        miembroClub.setCorreo(miembro.getCorreo());
        miembroClub.setTelefono(miembro.getTelefono());

        miembro.setCorreo(miembroClub.getCorreo());
        miembro.setTelefono(miembroClub.getTelefono());
        miembro.setNombresCompletos(miembroClub.getNombresCompletos());

        return miembro;
    }
}
