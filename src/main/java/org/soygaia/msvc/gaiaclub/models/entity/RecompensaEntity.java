package org.soygaia.msvc.gaiaclub.models.entity;

import io.smallrye.common.constraint.NotNull;
import jakarta.persistence.*;
import org.soygaia.msvc.gaiaclub.models.dtos.ProductoDTO;

@Entity
@Table(name = "t_recompensas")
public class RecompensaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rec_id")
    private Long recId;

    @JoinColumn(name = "rec_producto", referencedColumnName = "prd_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductoDTO recIdProducto;

    @Column(name = "rec_nombre")
    private String recNombre;

    @NotNull
    @Column(name = "rec_puntosreq")
    private int recPuntosReq;

    @Column(name = "rec_descripcion")
    private String recDescripcion;

    @Column(name = "rec_stock")
    private int stock;

    @JoinColumn(name = "rec_periodo", referencedColumnName = "per_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Long recIdPeriodo;

    public RecompensaEntity() {
    }

    public Long getRecId() {
        return recId;
    }

    public void setRecId(Long recId) {
        this.recId = recId;
    }

    public ProductoDTO getRecIdProducto() {
        return recIdProducto;
    }

    public void setRecIdProducto(ProductoDTO recIdProducto) {
        this.recIdProducto = recIdProducto;
    }

    public String getRecNombre() {
        return recNombre;
    }

    public void setRecNombre(String recNombre) {
        this.recNombre = recNombre;
    }

    public int getRecPuntosReq() {
        return recPuntosReq;
    }

    public void setRecPuntosReq(int recPuntosReq) {
        this.recPuntosReq = recPuntosReq;
    }

    public String getRecDescripcion() {
        return recDescripcion;
    }

    public void setRecDescripcion(String recDescripcion) {
        this.recDescripcion = recDescripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Long getRecIdPeriodo() {
        return recIdPeriodo;
    }

    public void setRecIdPeriodo(Long recIdPeriodo) {
        this.recIdPeriodo = recIdPeriodo;
    }
}
