package org.soygaia.msvc.gaiaclub.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.soygaia.msvc.gaiaclub.models.entity.PuntosEntity;
import org.soygaia.msvc.gaiaclub.models.entity.RecompensaEntity;

import java.util.List;

@ApplicationScoped
@Transactional
public class PuntosRepository implements PanacheRepository<PuntosEntity> {
    public List<PuntosEntity> recompensasDisponibles(Long clienteID) {
        return find("estado === \"VIGENTE\" AND idCliente", clienteID).list();
    }


}
