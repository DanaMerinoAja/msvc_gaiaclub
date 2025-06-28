package org.soygaia.msvc.gaiaclub.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.panleadministracion.recompensas.DeleteResponse;
import org.soygaia.msvc.gaiaclub.models.dtos.admin.panleadministracion.vales.ValeBaseDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.miembro.MiembroInfoActDTO;
import org.soygaia.msvc.gaiaclub.models.dtos.cliente_ecommerce.recompensas.vales.*;
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

    public List<ValeClienteEcommerceDTO> valesPorCliente(Long idMiembroClub) {

        List<ValeClienteEntity> listVales = valeClienteRepository.findValesCliente(idMiembroClub);

        return listVales.stream().map(v -> {

            ValePeriodoEntity vp = v.getValePeriodo();
            ValeEntity valeBase = vp.getVale();

            return new ValeClienteEcommerceDTO(
                    vp.getId(),
                    vp.getId(),
                    v.getMiembro().getId(),
                    v.getFechaCaducidad().toString(),
                    v.getValorPuntos(),
                    new ValePeriodoEcommerceDTO(
                            vp.getId(),
                            valeBase.getValorSoles(),
                            valeBase.getNombre(),
                            valeBase.getDescripcion(),
                            valeBase.getVigenciaDias(),
                            valeBase.getPuntosRequeridos(),
                            vp.getPeriodo().getId()
                    )
            );
        }).toList();
    }

    public List<ValeClienteDTO> valesClientePeriodo(Long idMiembro, Long idPeriodo){
        List<ValeClienteEntity> listVales = valeClienteRepository.findValesClientePeriodo(idMiembro, idPeriodo);
        return listVales.stream().map(this::convertirADTO).collect(Collectors.toList());
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
            vc.setFechaAplicacion(LocalDate.now());
            return new EstadoCanjeVale(true, convertirADTO(vc));
        }
        return new EstadoCanjeVale(false, convertirADTO(vc));
    }

    private ValeClienteDTO convertirADTO(ValeClienteEntity v) {
        ValePeriodoEntity valePeriodo = v.getValePeriodo();
        ValeEntity valeBase = valePeriodo.getVale();

        return new ValeClienteDTO(
                v.getId(),
                valePeriodo.getId(),
                v.getMiembro().getId(),
                v.getFechaCaducidad(),
                v.getValorPuntos(),
                new ValePeriodoDTO(
                        valePeriodo.getId(),
                        valePeriodo.getPeriodo().getId(),
                        valePeriodo.getActivo(),
                        new ValeBaseDTO(
                                valeBase.getId(),
                                valeBase.getValorSoles(),
                                valeBase.getNombre(),
                                valeBase.getDescripcion(),
                                valeBase.getVigenciaDias(),
                                valeBase.getPuntosRequeridos()
                        )
                )
        );
    }

    // Listar todos los vales base
    public List<ValeBaseDTO> listarVales() {
        return valeRepository.listAll().stream()
                .map(v -> new ValeBaseDTO(
                        v.getId(),
                        v.getValorSoles(),
                        v.getNombre(),
                        v.getDescripcion(),
                        v.getVigenciaDias(),
                        v.getPuntosRequeridos()
                ))
                .collect(Collectors.toList());
    }

    // Listar vales activos asignados a un periodo específico
    public List<ValePeriodoEcommerceDTO> listarValesActivosPorPeriodo(Long periodoId) {
        List<ValePeriodoEntity> valesPeriodo = valePeriodoRepository.find("periodo.id=?1 AND activo =?2", periodoId, true).list();

        return valesPeriodo.stream().map(vp -> {
            ValeEntity valeBase = vp.getVale();
            return new ValePeriodoEcommerceDTO(
                    vp.getId(),
                    valeBase.getValorSoles(),
                    valeBase.getNombre(),
                    valeBase.getDescripcion(),
                    valeBase.getVigenciaDias(),
                    valeBase.getPuntosRequeridos(),
                    vp.getPeriodo().getId()
            );
        }).collect(Collectors.toList());
    }

    // Listar vales activos asignados a un periodo específico
    public List<ValePeriodoDTO> listarValesPorPeriodo(Long periodoId) {
        List<ValePeriodoEntity> valesPeriodo = valePeriodoRepository.find("periodo.id=?1", periodoId).list();

        return valesPeriodo.stream().map(vp -> {
            ValeEntity valeBase = vp.getVale();
            return new ValePeriodoDTO(
                    vp.getId(),
                    vp.getPeriodo().getId(),
                    vp.getActivo(),
                    new ValeBaseDTO(
                            valeBase.getId(),
                            valeBase.getValorSoles(),
                            valeBase.getNombre(),
                            valeBase.getDescripcion(),
                            valeBase.getVigenciaDias(),
                            valeBase.getPuntosRequeridos()
                    )
            );
        }).collect(Collectors.toList());
    }

    // Crear nuevo vale
    public ValePeriodoDTO crearVale(ValePeriodoDTO dto) {
        ValeEntity vale = new ValeEntity();
        vale.setNombre("Vale por S/." + dto.getValeBase().getDescuentoSoles());
        vale.setDescripcion("Válido hasta " + dto.getValeBase().getVigencia() + " días después del canje");
        vale.setValorSoles(dto.getValeBase().getDescuentoSoles());
        vale.setPuntosRequeridos(dto.getValeBase().getPuntosRequeridos());
        vale.setVigenciaDias(dto.getValeBase().getVigencia());
        valeRepository.persist(vale);
        ValePeriodoEntity vp = new ValePeriodoEntity();
        vp.setVale(vale);
        vp.setPeriodo(periodoRepository.findById(dto.getPeriodoId()));
        vp.setActivo(true);
        valePeriodoRepository.persist(vp);
        return new ValePeriodoDTO(
                vp.getId(),
                vp.getPeriodo().getId(),
                vp.getActivo(),
                new ValeBaseDTO(
                        vale.getId(),
                        vale.getValorSoles(),
                        vale.getNombre(),
                        vale.getDescripcion(),
                        vale.getVigenciaDias(),
                        vale.getPuntosRequeridos()
                )
        );
    }

    // Asignar vale a un periodo
    public ValePeriodoDTO asignarValeAPeriodo(Long valeBaseId, Long periodoId) {
        ValeEntity valeBase = valeRepository.findById(valeBaseId);
        PeriodoEntity periodo = periodoRepository.findById(periodoId);

        if (valeBase == null || periodo == null) {
            throw new IllegalArgumentException("Vale o Periodo no encontrado");
        }

        ValePeriodoEntity vp = new ValePeriodoEntity();
        vp.setVale(valeBase);
        vp.setPeriodo(periodo);
        vp.setActivo(true);
        valePeriodoRepository.persist(vp);
        return new ValePeriodoDTO(
                vp.getId(),
                vp.getPeriodo().getId(),
                vp.getActivo(),
                new ValeBaseDTO(
                        valeBase.getId(),
                        valeBase.getValorSoles(),
                        valeBase.getNombre(),
                        valeBase.getDescripcion(),
                        valeBase.getVigenciaDias(),
                        valeBase.getPuntosRequeridos()
                )
        );
    }

    public ValePeriodoDTO setValePeriodo(Long valePeriodoId){
        ValePeriodoEntity vp = valePeriodoRepository.findById(valePeriodoId);
        vp.setActivo(!vp.getActivo());
        ValeEntity vale = vp.getVale();
        return new ValePeriodoDTO(
                vp.getId(),
                vp.getPeriodo().getId(),
                vp.getActivo(),
                new ValeBaseDTO(
                        vale.getId(),
                        vale.getValorSoles(),
                        vale.getNombre(),
                        vale.getDescripcion(),
                        vale.getVigenciaDias(),
                        vale.getPuntosRequeridos()
                )
        );
    }

    public DeleteResponse eliminarValePeriodo(Long valePeriodoID){
        ValePeriodoEntity vp = valePeriodoRepository.findById(valePeriodoID);

        if (vp == null) {
            return new DeleteResponse(-1L, "Vale no encontrado", false);
        }
        long cantidadCanjes = valeClienteRepository.count("valePeriodo.id", valePeriodoID);

        if (cantidadCanjes > 0) {
            vp.setActivo(false);

            return new DeleteResponse(valePeriodoID, "Tiene canjes registrados. Se inactivó el vale.", false);
        }

        valePeriodoRepository.deleteById(valePeriodoID);
        return new DeleteResponse(valePeriodoID, "Vale eliminado", true);
    }
}