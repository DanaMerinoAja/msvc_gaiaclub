package org.soygaia.msvc.gaiaclub.services;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.bonificaciones.BonificacionDTO;
import org.soygaia.msvc.gaiaclub.models.entity.BonificacionEntity;
import org.soygaia.msvc.gaiaclub.repositories.BonificacionRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class BonificacionService {
    @Inject
    BonificacionRepository bonificacionRepository;

    public List<BonificacionDTO> findAll(){
        return bonificacionRepository.findUltimasVersiones().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public BonificacionDTO crearBonificacion(BonificacionDTO dto){
        BonificacionEntity bonificacionEntity = new BonificacionEntity();
        bonificacionEntity.setDescripcion(dto.getDescripcion());
        bonificacionEntity.setNombre(dto.getNombre());
        bonificacionEntity.setPuntos(dto.getPuntos());
        bonificacionEntity.setFechaCreacion(LocalDateTime.now());
        bonificacionEntity.setActiva(true);
        bonificacionRepository.persist(bonificacionEntity);
        bonificacionRepository.flush();
        bonificacionEntity.setBonificacionOrigen(bonificacionEntity);
        return toDTO(bonificacionEntity);
    }

    public BonificacionDTO modificarBonificacion(BonificacionDTO dto) {

        BonificacionEntity bAnt = bonificacionRepository.findById(dto.getId());
        bAnt.setFechaModificacion(LocalDateTime.now());
        bAnt.setActiva(false);

        BonificacionEntity bonificacionEntity = new BonificacionEntity();
        bonificacionEntity.setDescripcion(dto.getDescripcion());
        bonificacionEntity.setNombre(dto.getNombre());
        bonificacionEntity.setPuntos(dto.getPuntos());
        bonificacionEntity.setActiva(true);
        bonificacionEntity.setFechaCreacion(LocalDateTime.now());

        BonificacionEntity ultimaBonificacion = bAnt.getBonificacionOrigen();

        bonificacionEntity.setBonificacionOrigen(Objects.requireNonNullElse(ultimaBonificacion, bAnt));

        bonificacionRepository.persist(bonificacionEntity);
        return toDTO(bonificacionEntity);
    }


    public BonificacionDTO restaurarAnterior(Long idAct, Long idAnt){
        BonificacionEntity boniAnt = bonificacionRepository.findById(idAnt);
        BonificacionEntity boniAct = bonificacionRepository.findById(idAct);

        boniAnt.setActiva(true);
        boniAct.setActiva(false);

        return toDTO(boniAct);
    }

    public BonificacionDTO setEstadoBonificacion(Long id){
        BonificacionEntity boni = bonificacionRepository.findById(id);
        boni.setActiva(!boni.isActiva());
        return toDTO(boni);
    }

    private BonificacionDTO toDTO(BonificacionEntity b){
        return new BonificacionDTO(b.getId(), b.getNombre(), b.getDescripcion(), b.getPuntos(), b.isActiva(), b.getFechaCreacion());
    }

    public List<BonificacionDTO> obtenerHistorial(Long idBonificacion) {
        BonificacionEntity bonificacion = bonificacionRepository.findById(idBonificacion);
        BonificacionEntity bOrigen =bonificacion.getBonificacionOrigen();

        List<BonificacionEntity> lista = new ArrayList<>();
        if(!Objects.equals(bOrigen.getId(), bonificacion.getId())){
            lista = bonificacionRepository.find(
                    "SELECT b FROM BonificacionEntity b WHERE " +
                            "(b.bonificacionOrigen.id = ?1 AND b.id != ?2) OR b.id = ?1 ORDER BY b.fechaCreacion ASC",
                    bOrigen.getId(), idBonificacion
            ).list();
        } else {
            lista = bonificacionRepository.find(
                    "SELECT b FROM BonificacionEntity b WHERE " +
                            "(b.bonificacionOrigen.id = ?1 AND b.id != ?1) ORDER BY b.fechaCreacion ASC",
                    idBonificacion
            ).list();
        }
        return lista.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private BonificacionEntity buscarUltimaBonificacion(BonificacionEntity inicio) {
        BonificacionEntity actual = inicio;

        while (true) {
            BonificacionEntity hijo = bonificacionRepository.find(
                    "SELECT b FROM BonificacionEntity b WHERE b.bonificacionAnterior.id = ?1",
                    actual.getId()
            ).firstResult();

            if (hijo == null) {
                return actual; // Es la última de la línea
            }
            actual = hijo;
        }
    }

    public List<BonificacionDTO> getBonificacionesActivas() {
        return bonificacionRepository.find("activa", true).stream().map(b -> {
                    return new BonificacionDTO(b.getNombre(), b.getDescripcion(), b.getPuntos());
                }).toList();
    }
}
