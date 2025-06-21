package org.soygaia.msvc.gaiaclub.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.soygaia.msvc.gaiaclub.models.dtos.GeneralInfoDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.panleadministracion.GeneralInfoAdminDTO;
import org.soygaia.msvc.gaiaclub.models.entity.GeneralInfoEntity;
import org.soygaia.msvc.gaiaclub.repositories.GeneralInfoRepository;

@ApplicationScoped
@Transactional
public class GeneralInfoService {

    @Inject
    GeneralInfoRepository generalInfoRepository;

    public GeneralInfoDTO getGenInfoCliente(){
        GeneralInfoEntity gInf = generalInfoRepository.findAll().firstResult();
        return new GeneralInfoDTO(gInf.getPuntosPorCompra(), gInf.getValorCompra(), gInf.getValorPuntos());
    }

    public GeneralInfoEntity guardarOActualizarInfoGeneralAdmin(GeneralInfoAdminDTO genInfDTO){
        GeneralInfoEntity entity = new GeneralInfoEntity();

        entity.setPuntosBienvenida(genInfDTO.getPuntosBienvenida());
        entity.setPuntosPorCompra(genInfDTO.getPuntosPorCompra());
        entity.setValorCompra(genInfDTO.getValorCompra());
        entity.setValorPuntos(genInfDTO.getValorPuntos());
        entity.setPuntosVigenciaMeses(genInfDTO.getPuntosVigenciaMeses());

        generalInfoRepository.persist(entity);

        return entity;
    }
}
