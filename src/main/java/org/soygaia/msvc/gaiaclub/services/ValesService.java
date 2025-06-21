package org.soygaia.msvc.gaiaclub.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.miembro.MiembroInfoActDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.vales.EstadoCanjeVale;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.vales.ValeDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.vales.RegistroValeClienteDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.vales.ValeClienteDTO;
import org.soygaia.msvc.gaiaclub.models.entity.*;
import org.soygaia.msvc.gaiaclub.repositories.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class ValesService {

    @Inject
    private MiembroRepository miembroRepository;

    @Inject
    private ValeClienteRepository valeClienteRepository;

    @Inject
    private ValePeriodoRepository valePeriodoRepository;

    @Inject
    private ValeRepository valeRepository;

    @Inject
    private PeriodoRepository periodoRepository;

    @Inject
    private PuntosService puntosService;

    public List<ValeClienteEntity> valesPorCliente(Long idMiembroClub) {
        return valeClienteRepository.findValesCliente(idMiembroClub);
    }

    public MiembroInfoActDTO guardarValeCliente(Long miembroId, Long valePeriodoId) {
        MiembroClubEntity miembro = miembroRepository.findById(miembroId);
        ValePeriodoEntity valePeriodo = valePeriodoRepository.findById(valePeriodoId);

        if (miembro == null || valePeriodo == null) {
            throw new IllegalArgumentException("Miembro o vale no encontrado");
        }

        if(puntosService.getTotalPuntosDisponiblesPorCliente(miembroId)>=valePeriodo.getVale().getPuntosRequeridos()){
            ValeClienteEntity valeCliente = new ValeClienteEntity();
            valeCliente.setMiembro(miembro);
            valeCliente.setValePeriodo(valePeriodo);
            valeCliente.setFechaCanje(LocalDate.now());
            valeCliente.setFechaCaducidad(LocalDate.now().plusDays(valePeriodo.getVale().getVigenciaDias()));
            valeCliente.setValorPuntos(valePeriodo.getVale().getPuntosRequeridos());
            valeCliente.setEstado(ValeClienteEntity.EstadoValeCliente.VIGENTE);

            puntosService.canjearPuntos(miembroId, valeCliente.getValorPuntos());

            Long newPuntos = puntosService.getTotalPuntosDisponiblesPorCliente(miembroId);
            Long newPuntosVencer = puntosService.getTotalPuntosCercanosVencerPorCliente(miembroId);

            valeClienteRepository.persist(valeCliente);

            return new MiembroInfoActDTO(convertirADTO(valeCliente), newPuntos, newPuntosVencer);
        }
        return null;
    }

    public EstadoCanjeVale canjearVale(ValeClienteDTO valeClienteDTO) {
        ValeClienteEntity vc = valeClienteRepository.findById(valeClienteDTO.getIdValeCliente());
        ValeClienteEntity.EstadoValeCliente estadoValeCliente = vc.getEstado();
        if(estadoValeCliente!= ValeClienteEntity.EstadoValeCliente.USADO && estadoValeCliente!= ValeClienteEntity.EstadoValeCliente.CADUCADO){
            vc.setEstado(ValeClienteEntity.EstadoValeCliente.USADO);
            return new EstadoCanjeVale(true, convertirADTO(vc));
        }
        return new EstadoCanjeVale(false, convertirADTO(vc));
    }

    private ValeClienteDTO convertirADTO(ValeClienteEntity v) {
        ValeClienteDTO dto = new ValeClienteDTO();
        dto.setIdValeCliente(v.getId());
        dto.setIdMiembro(v.getMiembro().getId());
        dto.setFechaCaducidad(v.getFechaCaducidad());
        dto.setPuntos(v.getValorPuntos());

        ValePeriodoEntity vp = v.getValePeriodo();
        dto.setIdValePeriodo(vp.getId());

        dto.setVale(new ValeDTO(
                vp.getId(),
                vp.getVale().getValorSoles(),
                vp.getVale().getNombre(),
                vp.getVale().getDescripcion(),
                vp.getVale().getVigenciaDias(),
                vp.getVale().getPuntosRequeridos(),
                vp.getPeriodo().getId()
        ));
        return dto;
    }

    // Listar todos los vales base (sin importar el periodo)
    public List<ValeDTO> listarVales() {
        return valeRepository.listAll().stream()
                .map(v -> new ValeDTO(
                        v.getId(),
                        v.getValorSoles(),
                        v.getNombre(),
                        v.getDescripcion(),
                        v.getVigenciaDias(),
                        v.getPuntosRequeridos(),
                        null // sin periodo
                ))
                .collect(Collectors.toList());
    }

    // Listar vales activos asignados a un periodo específico
    public List<ValeDTO> listarValesPorPeriodo(Long periodoId) {
        List<ValePeriodoEntity> valesPeriodo = valePeriodoRepository.find("periodo.id", periodoId).list();

        return valesPeriodo.stream().map(vp -> {
            ValeEntity v = vp.getVale();
            return new ValeDTO(
                    v.getId(),
                    v.getValorSoles(),
                    v.getNombre(),
                    v.getDescripcion(),
                    v.getVigenciaDias(),
                    v.getPuntosRequeridos(),
                    vp.getPeriodo().getId()
            );
        }).collect(Collectors.toList());
    }


    // Crear nuevo vale base
    public ValeEntity crearVale(ValeDTO dto) {
        ValeEntity vale = new ValeEntity();
        vale.setNombre("Vale por S/." + dto.getDescuentoSoles());
        vale.setDescripcion("Válido hasta " + dto.getVigencia() + " días después del canje");
        vale.setValorSoles(dto.getDescuentoSoles());
        vale.setPuntosRequeridos(dto.getPuntosRequeridos());
        vale.setVigenciaDias(dto.getVigencia());
        valeRepository.persist(vale);
        ValePeriodoEntity vp = new ValePeriodoEntity();
        vp.setVale(vale);
        vp.setPeriodo(periodoRepository.findById(dto.getPeriodoId()));
        vp.setActivo(true);
        valePeriodoRepository.persist(vp);
        return vale;
    }

    // Asignar vale a un periodo
    public ValePeriodoEntity asignarValeAPeriodo(Long valeId, Long periodoId) {
        ValeEntity vale = valeRepository.findById(valeId);
        PeriodoEntity periodo = periodoRepository.findById(periodoId);

        if (vale == null || periodo == null) {
            throw new IllegalArgumentException("Vale o Periodo no encontrado");
        }

        ValePeriodoEntity vp = new ValePeriodoEntity();
        vp.setVale(vale);
        vp.setPeriodo(periodo);
        vp.setActivo(true);
        valePeriodoRepository.persist(vp);
        return vp;
    }

    // Editar vale base
    public ValeEntity editarVale(Long id, ValeDTO dto) {
        ValeEntity vale = valeRepository.findById(id);
        if (vale == null) throw new NotFoundException("Vale no encontrado");

        vale.setNombre(dto.getNombreVale());
        vale.setDescripcion(dto.getDescripcionVale());
        vale.setValorSoles(dto.getDescuentoSoles());
        vale.setPuntosRequeridos(dto.getPuntosRequeridos());
        vale.setVigenciaDias(dto.getVigencia());
        return vale;
    }

    // Eliminar vale base (si no está asociado a ningún periodo)
    public void eliminarVale(Long id) {
        ValeEntity vale = valeRepository.findById(id);
        if (vale != null) {
            valeRepository.delete(vale);
        }
    }

    // Eliminar vínculo entre vale y periodo
    public void eliminarAsignacionDePeriodo(Long valePeriodoId) {
        ValePeriodoEntity vp = valePeriodoRepository.findById(valePeriodoId);
        if (vp != null) {
            valePeriodoRepository.delete(vp);
        }
    }
}