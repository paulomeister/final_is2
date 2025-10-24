package com.serviciudad.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "facturas_acueducto")
@Data
@AllArgsConstructor
@NoArgsConstructor
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

}
