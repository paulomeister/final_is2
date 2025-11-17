package com.serviciudad.service;

import com.serviciudad.dto.DeudaConsolidadaDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DeudaConsolidadaBuilderTest {

    @Test
    void buildWithNoServicesTotalsZero() {
        DeudaConsolidadaBuilder b = new DeudaConsolidadaBuilder()
                .withClienteId("123")
                .withNombre("Cliente")
                .withFecha(OffsetDateTime.now());

        DeudaConsolidadaDTO dto = b.build();
        assertNotNull(dto);
        assertEquals(BigDecimal.ZERO, dto.getTotalAPagar());
    }

    @Test
    void buildWithValuesSumsCorrectly() {
        DeudaConsolidadaBuilder b = new DeudaConsolidadaBuilder();
        b.withClienteId("321");
        b.withNombre("Otro");
        b.withFecha(OffsetDateTime.now());

        b.withEnergia("202301", "10 kWh", new BigDecimal("100.50"));
        b.withAcueducto("202301", "5 m³", new BigDecimal("50.25"));

        DeudaConsolidadaDTO dto = b.build();
        assertNotNull(dto.getEnergia());
        assertNotNull(dto.getAcueducto());
        assertEquals(new BigDecimal("150.75"), dto.getTotalAPagar());
        assertEquals("202301", dto.getEnergia().getPeriodo());
        assertEquals("10 kWh", dto.getEnergia().getConsumo());
    }

    @Test
    void buildHandlesNullValuesAsZero() {
        DeudaConsolidadaBuilder b = new DeudaConsolidadaBuilder();
        b.withClienteId("999");
        b.withNombre("Null Cliente");
        b.withFecha(OffsetDateTime.now());

        // valorPagar null en ambos servicios debe tratarse como BigDecimal.ZERO
        b.withEnergia("202301", "0 kWh", null);
        b.withAcueducto("202301", "0 m³", null);

        DeudaConsolidadaDTO dto = b.build();
        assertNotNull(dto.getEnergia());
        assertNotNull(dto.getAcueducto());
        assertEquals(BigDecimal.ZERO, dto.getEnergia().getValorPagar());
        assertEquals(BigDecimal.ZERO, dto.getAcueducto().getValorPagar());
        assertEquals(BigDecimal.ZERO, dto.getTotalAPagar());
    }
}
