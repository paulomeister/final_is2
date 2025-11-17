package com.serviciudad.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacturaEnergia {
    private String idCliente;
    private String periodo;
    private Integer consumoKwh;
    private BigDecimal valorPagar;

    }
