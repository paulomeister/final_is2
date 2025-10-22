package com.serviciudad.service;

import com.serviciudad.dto.DeudaConsolidadaDTO;
import com.serviciudad.dto.DetalleServicioDTO;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class DeudaConsolidadaBuilder {
    private final DeudaConsolidadaDTO dto = new DeudaConsolidadaDTO();

    public DeudaConsolidadaBuilder withClienteId(String id) { dto.setClienteId(id); return this; }
    public DeudaConsolidadaBuilder withNombre(String nombre) { dto.setNombreCliente(nombre); return this; }
    public DeudaConsolidadaBuilder withFecha(OffsetDateTime fecha) { dto.setFechaConsulta(fecha); return this; }

    public DeudaConsolidadaBuilder withEnergia(String periodo, String consumo, BigDecimal valor) {
        DetalleServicioDTO d = new DetalleServicioDTO();
        d.setPeriodo(periodo);
        d.setConsumo(consumo);
        d.setValorPagar(valor == null ? BigDecimal.ZERO : valor);
        dto.setEnergia(d);
        return this;
    }

    public DeudaConsolidadaBuilder withAcueducto(String periodo, String consumo, BigDecimal valor) {
        DetalleServicioDTO d = new DetalleServicioDTO();
        d.setPeriodo(periodo);
        d.setConsumo(consumo);
        d.setValorPagar(valor == null ? BigDecimal.ZERO : valor);
        dto.setAcueducto(d);
        return this;
    }

    public DeudaConsolidadaDTO build() {
        BigDecimal e = dto.getEnergia() != null && dto.getEnergia().getValorPagar() != null ? dto.getEnergia().getValorPagar() : BigDecimal.ZERO;
        BigDecimal a = dto.getAcueducto() != null && dto.getAcueducto().getValorPagar() != null ? dto.getAcueducto().getValorPagar() : BigDecimal.ZERO;
        dto.setTotalAPagar(e.add(a));
        return dto;
    }
}
