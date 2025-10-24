package com.serviciudad.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Modelo simple (no JPA) que representa la factura leída desde el archivo ancho-fijo.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacturaEnergia {
    private String idCliente;
    private String periodo;
    private Integer consumoKwh;
    private BigDecimal valorPagar;

    }
