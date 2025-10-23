package com.serviciudad.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleServicioDTO {

    private String periodo;
    private String consumo;
    private BigDecimal valorPagar;

}
