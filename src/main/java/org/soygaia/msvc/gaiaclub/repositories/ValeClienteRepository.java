package org.soygaia.msvc.gaiaclub.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.soygaia.msvc.gaiaclub.models.entity.MiembroClubEntity;
import org.soygaia.msvc.gaiaclub.models.entity.ValeClienteEntity;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
@Transactional
public class ValeClienteRepository implements PanacheRepository<ValeClienteEntity> {

    public List<ValeClienteEntity> findValesCliente(MiembroClubEntity miembroClub){

        return find("miembroClub.idMiembro = ?1 AND fechaCaducidad > ?2", miembroClub.getClienteId(), LocalDate.now()).list();
    }
}
