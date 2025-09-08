package org.soygaia.msvc.gaiaclub.jobs;

import io.quarkus.scheduler.Scheduled;
import io.smallrye.common.annotation.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.soygaia.msvc.gaiaclub.services.PuntosService;

@ApplicationScoped
public class ActualizarVigenciaPuntosJob {

    @Inject
    PuntosService puntosService;

    @Scheduled(cron = "0 0 0 * * ?", timeZone = "America/Lima")
    @Blocking
    public void runDaily() {
        puntosService.revisarVigencia();
    }
}
