package com.serviciudad.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class DeudaConsolidadaDTO {
    private String clienteId;
    private String nombreCliente;
    private OffsetDateTime fechaConsulta;
    private DetalleServicioDTO energia;
    private DetalleServicioDTO acueducto;
    private BigDecimal totalAPagar;

    public String getClienteId() { return clienteId; }
    public void setClienteId(String clienteId) { this.clienteId = clienteId; }
    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    public OffsetDateTime getFechaConsulta() { return fechaConsulta; }
    public void setFechaConsulta(OffsetDateTime fechaConsulta) { this.fechaConsulta = fechaConsulta; }
    public DetalleServicioDTO getEnergia() { return energia; }
    public void setEnergia(DetalleServicioDTO energia) { this.energia = energia; }
    public DetalleServicioDTO getAcueducto() { return acueducto; }
    public void setAcueducto(DetalleServicioDTO acueducto) { this.acueducto = acueducto; }
    public BigDecimal getTotalAPagar() { return totalAPagar; }
    public void setTotalAPagar(BigDecimal totalAPagar) { this.totalAPagar = totalAPagar; }
}
