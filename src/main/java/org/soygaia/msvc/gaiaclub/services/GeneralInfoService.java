package org.soygaia.msvc.gaiaclub.services;

import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.GeneralInfoDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.panleadministracion.GeneralInfoAdminDTO;
import org.soygaia.msvc.gaiaclub.models.entity.GeneralInfoEntity;
import org.soygaia.msvc.gaiaclub.repositories.GeneralInfoRepository;

import java.time.LocalDate;

@ApplicationScoped
@Transactional
public class GeneralInfoService {

    @Inject
    GeneralInfoRepository generalInfoRepository;

    public GeneralInfoDTO getGenInfoCliente(){
        GeneralInfoEntity gInf = generalInfoRepository.findAll(Sort.descending("id")).firstResult();
        return new GeneralInfoDTO(gInf.getPuntosPorCompra(), gInf.getValorCompra(), gInf.getValorPuntos());
    }

    public GeneralInfoEntity getGenInfoAdmin(){
        return generalInfoRepository.findAll(Sort.descending("id")).firstResult();
    }

    public GeneralInfoEntity actualizarInfoGeneralAdmin(GeneralInfoAdminDTO genInfDTO){
        GeneralInfoEntity entity = new GeneralInfoEntity();

        entity.setPuntosBienvenida(genInfDTO.getPuntosBienvenida());
        entity.setPuntosPorCompra(genInfDTO.getPuntosPorCompra());
        entity.setValorCompra(genInfDTO.getValorCompra());
        entity.setValorPuntos(genInfDTO.getValorPuntos());
        entity.setPuntosVigenciaMeses(genInfDTO.getPuntosVigenciaMeses());
        entity.setFechaActualizacion(LocalDate.now());
        entity.setAlertaVencimiento(genInfDTO.getAlertaVencimiento());

        generalInfoRepository.persist(entity);

        return entity;
    }

}
