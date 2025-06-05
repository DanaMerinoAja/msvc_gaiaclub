package org.soygaia.msvc.gaiaclub.models.dtos.ecommerce;

import java.time.LocalDate;

public class OrdenDTO {
    private Long id;
    private Long id_cliente;
    private LocalDate fecha;
    private double total;

    public OrdenDTO(Long id, Long id_cliente, LocalDate fecha, double total) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.fecha = fecha;
        this.total = total;
    }

    public OrdenDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
