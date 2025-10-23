package com.serviciudad.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class DeudaConsolidadaDTO {

    private String clienteId;
    private String nombreCliente;
    private OffsetDateTime fechaConsulta;
    private DetalleServicioDTO energia;
    private DetalleServicioDTO acueducto;
    private BigDecimal totalAPagar;

}
