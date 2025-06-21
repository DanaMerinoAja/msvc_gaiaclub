package org.soygaia.msvc.gaiaclub.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "t_general_info")
public class GeneralInfoEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "gi_pbienvenida", nullable = false)
  private int puntosBienvenida;

  @Column(name = "gi_pporcompra", nullable = false)
  private int puntosPorCompra;

  @Column(name = "gi_valorcompra", nullable = false)
  private double valorCompra;

  @Column(name = "gi_vigenciapuntos", nullable = false)
  private int puntosVigenciaMeses;

  @Column(name = "gi_valorpuntos", nullable = false)
  private double valorPuntos;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getPuntosBienvenida() {
    return puntosBienvenida;
  }

  public void setPuntosBienvenida(int puntosBienvenida) {
    this.puntosBienvenida = puntosBienvenida;
  }

  public int getPuntosPorCompra() {
    return puntosPorCompra;
  }

  public void setPuntosPorCompra(int puntosPorCompra) {
    this.puntosPorCompra = puntosPorCompra;
  }

  public double getValorCompra() {
    return valorCompra;
  }

  public void setValorCompra(double valorCompra) {
    this.valorCompra = valorCompra;
  }

  public int getPuntosVigenciaMeses() {
    return puntosVigenciaMeses;
  }

  public void setPuntosVigenciaMeses(int puntosVigenciaMeses) {
    this.puntosVigenciaMeses = puntosVigenciaMeses;
  }

  public double getValorPuntos() {
    return valorPuntos;
  }

  public void setValorPuntos(double valorPuntos) {
    this.valorPuntos = valorPuntos;
  }
}