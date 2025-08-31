package org.soygaia.msvc.gaiaclub.models.dtos.admin.productos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListItem {
    private Long pvtId;
    private Double pvtPreciominimo;
    private Double pvtPreciomaximo;
    private String pvtEstado;
    private Producto pvtProducto;          // <- donde vienen los datos del producto
    private UnidadMedida pvtUnidadmedida;  // (si lo necesitas)

    // getters y setters
    public Long getPvtId() { return pvtId; }
    public void setPvtId(Long pvtId) { this.pvtId = pvtId; }
    public Double getPvtPreciominimo() { return pvtPreciominimo; }
    public void setPvtPreciominimo(Double pvtPreciominimo) { this.pvtPreciominimo = pvtPreciominimo; }
    public Double getPvtPreciomaximo() { return pvtPreciomaximo; }
    public void setPvtPreciomaximo(Double pvtPreciomaximo) { this.pvtPreciomaximo = pvtPreciomaximo; }
    public String getPvtEstado() { return pvtEstado; }
    public void setPvtEstado(String pvtEstado) { this.pvtEstado = pvtEstado; }
    public Producto getPvtProducto() { return pvtProducto; }
    public void setPvtProducto(Producto pvtProducto) { this.pvtProducto = pvtProducto; }
    public UnidadMedida getPvtUnidadmedida() { return pvtUnidadmedida; }
    public void setPvtUnidadmedida(UnidadMedida pvtUnidadmedida) { this.pvtUnidadmedida = pvtUnidadmedida; }
}