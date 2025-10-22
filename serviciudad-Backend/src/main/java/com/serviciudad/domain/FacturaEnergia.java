package com.serviciudad.domain;

import java.math.BigDecimal;

/**
 * Modelo simple (no JPA) que representa la factura leída desde el archivo ancho-fijo.
 */
public class FacturaEnergia {
    private String idCliente;
    private String periodo;
    private Integer consumoKwh;
    private BigDecimal valorPagar;

    public String getIdCliente() { return idCliente; }
    public void setIdCliente(String idCliente) { this.idCliente = idCliente; }
    public String getPeriodo() { return periodo; }
    public void setPeriodo(String periodo) { this.periodo = periodo; }
    public Integer getConsumoKwh() { return consumoKwh; }
    public void setConsumoKwh(Integer consumoKwh) { this.consumoKwh = consumoKwh; }
    public BigDecimal getValorPagar() { return valorPagar; }
    public void setValorPagar(BigDecimal valorPagar) { this.valorPagar = valorPagar; }
}
