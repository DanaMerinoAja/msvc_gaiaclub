package org.soygaia.msvc.gaiaclub.jobs;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.soygaia.msvc.gaiaclub.config.properties.Constantes;
import org.soygaia.msvc.gaiaclub.models.entity.MiembroClubEntity;
import org.soygaia.msvc.gaiaclub.repositories.MiembroRepository;
import org.soygaia.msvc.gaiaclub.services.MailService;
import org.soygaia.msvc.gaiaclub.services.PuntosService;

import java.time.*;
import java.util.List;

@ApplicationScoped
public class NotifyPointsExpJob {

    @Inject
    MiembroRepository miembroRepository;
    @Inject
    PuntosService puntosService;
    @Inject
    MailService mailService;

    @Scheduled(cron = "0 0 8 * * ?", timeZone = "America/Lima")
    public void runDaily() {
        List<MiembroClubEntity> miembros = miembroRepository.listAll();

        for (MiembroClubEntity m : miembros) {
            long puntosPorVencer = puntosService.getTotalPuntosCercanosVencerPorCliente(m.getClienteId());
            if(puntosPorVencer>0){
                LocalDate fechaCaducidad = puntosService.getPuntosVigentesOrdenados(m.getClienteId()).getFirst().getFechaCaducidad();
                mailService.sendExpiryAlert(m.getCorreo(), puntosPorVencer, fechaCaducidad);
            }
        }
    }

}
