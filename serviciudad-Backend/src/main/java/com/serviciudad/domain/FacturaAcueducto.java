package com.serviciudad.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "facturas_acueducto")
public class FacturaAcueducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_cliente", nullable = false)
    private String idCliente;

    @Column(length = 6)
    private String periodo;

    @Column(name = "consumo_m3")
    private Integer consumoM3;

    @Column(name = "valor_pagar", precision = 12, scale = 2)
    private BigDecimal valorPagar;

    // Getters / Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getIdCliente() { return idCliente; }
    public void setIdCliente(String idCliente) { this.idCliente = idCliente; }
    public String getPeriodo() { return periodo; }
    public void setPeriodo(String periodo) { this.periodo = periodo; }
    public Integer getConsumoM3() { return consumoM3; }
    public void setConsumoM3(Integer consumoM3) { this.consumoM3 = consumoM3; }
    public java.math.BigDecimal getValorPagar() { return valorPagar; }
    public void setValorPagar(java.math.BigDecimal valorPagar) { this.valorPagar = valorPagar; }
}
