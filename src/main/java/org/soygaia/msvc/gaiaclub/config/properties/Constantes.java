package org.soygaia.msvc.gaiaclub.config.properties;

import io.quarkus.panache.common.Sort;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.soygaia.msvc.gaiaclub.models.entity.GeneralInfoEntity;
import org.soygaia.msvc.gaiaclub.repositories.GeneralInfoRepository;

@ApplicationScoped
public class Constantes {

    //Variables de políticas
    public static int mesesVigencia;
    public static int puntosPorCompra;
    public static double valorCompra;
    public static int bonificacionBienvenida;
    public static int alertVencimiento;

    public static String apiProductos = "http://192.168.10.62:5064/api-sucursal-prod/sucursal-producto/precio-venta";

    @Inject
    GeneralInfoRepository gi;

    @PostConstruct
    void inicializar(){
        GeneralInfoEntity generalInfo = gi.findAll(Sort.descending("id")).firstResult();
        mesesVigencia = generalInfo.getPuntosVigenciaMeses();
        puntosPorCompra = generalInfo.getPuntosPorCompra();
        valorCompra = generalInfo.getValorCompra();
        bonificacionBienvenida = generalInfo.getPuntosBienvenida();
        alertVencimiento = generalInfo.getAlertaVencimiento();
    }
}
