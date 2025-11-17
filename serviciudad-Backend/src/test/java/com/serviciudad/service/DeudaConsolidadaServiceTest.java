package com.serviciudad.service;

import com.serviciudad.adapter.AdaptadorArchivoEnergia;
import com.serviciudad.domain.FacturaAcueducto;
import com.serviciudad.domain.FacturaEnergia;
import com.serviciudad.dto.DeudaConsolidadaDTO;
import com.serviciudad.repository.FacturaAcueductoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DeudaConsolidadaServiceTest {

    @Test
    void consultarCombinesLatestEnergyAndAcueducto() {
        AdaptadorArchivoEnergia adapter = Mockito.mock(AdaptadorArchivoEnergia.class);
        FacturaAcueductoRepository repo = Mockito.mock(FacturaAcueductoRepository.class);
        ClienteService clienteService = Mockito.mock(ClienteService.class);

        // prepare energy invoices: two entries, latest should be periodo 202302
        FacturaEnergia e1 = new FacturaEnergia("1234500001", "202301", 100, new BigDecimal("100.00"));
        FacturaEnergia e2 = new FacturaEnergia("1234500001", "202302", 150, new BigDecimal("200.00"));
        when(adapter.leerFacturas()).thenReturn(List.of(e1, e2));

        // prepare acueducto invoices: single entry
        FacturaAcueducto a = new FacturaAcueducto();
        a.setIdCliente("1234500001");
        a.setPeriodo("202301");
        a.setConsumoM3(10);
        a.setValorPagar(new BigDecimal("50.00"));
        when(repo.findByIdCliente("1234500001")).thenReturn(List.of(a));

        when(clienteService.obtenerNombrePorId("1234500001")).thenReturn("Test Cliente");

        DeudaConsolidadaService service = new DeudaConsolidadaService(adapter, repo, clienteService);

        DeudaConsolidadaDTO dto = service.consultar("1234500001");

        assertNotNull(dto);
        assertEquals("1234500001", dto.getClienteId());
        assertEquals("Test Cliente", dto.getNombreCliente());
        // energy should be from periodo 202302
        assertEquals("202302", dto.getEnergia().getPeriodo());
        assertEquals(new BigDecimal("200.00"), dto.getEnergia().getValorPagar());
        // acueducto from periodo 202301
        assertEquals("202301", dto.getAcueducto().getPeriodo());
        assertEquals(new BigDecimal("50.00"), dto.getAcueducto().getValorPagar());
        // total = 200 + 50
        assertEquals(new BigDecimal("250.00"), dto.getTotalAPagar());
    }

    @Test
    void consultarHandlesMissingEnergyAndAcueducto() {
        AdaptadorArchivoEnergia adapter = Mockito.mock(AdaptadorArchivoEnergia.class);
        FacturaAcueductoRepository repo = Mockito.mock(FacturaAcueductoRepository.class);
        ClienteService clienteService = Mockito.mock(ClienteService.class);

        // sin facturas de energía ni de acueducto
        when(adapter.leerFacturas()).thenReturn(List.of());
        when(repo.findByIdCliente("no-data")).thenReturn(List.of());
        when(clienteService.obtenerNombrePorId("no-data")).thenReturn("Sin Datos");

        DeudaConsolidadaService service = new DeudaConsolidadaService(adapter, repo, clienteService);

        DeudaConsolidadaDTO dto = service.consultar("no-data");

        assertNotNull(dto);
        assertEquals("no-data", dto.getClienteId());
        assertEquals("Sin Datos", dto.getNombreCliente());

        // Debe rellenar con valores por defecto cuando no hay facturas
        assertEquals("-", dto.getEnergia().getPeriodo());
        assertEquals("0 kWh", dto.getEnergia().getConsumo());
        assertEquals("-", dto.getAcueducto().getPeriodo());
        assertEquals("0 m³", dto.getAcueducto().getConsumo());
        // total debería ser 0 porque ambos valores son BigDecimal.ZERO
        assertEquals(BigDecimal.ZERO, dto.getTotalAPagar());
    }

    @Test
    void consultarUsesLatestFacturaAcueductoByPeriodo() {
        AdaptadorArchivoEnergia adapter = Mockito.mock(AdaptadorArchivoEnergia.class);
        FacturaAcueductoRepository repo = Mockito.mock(FacturaAcueductoRepository.class);
        ClienteService clienteService = Mockito.mock(ClienteService.class);

        // sin facturas de energía, solo acueducto
        when(adapter.leerFacturas()).thenReturn(List.of());

        FacturaAcueducto a1 = new FacturaAcueducto();
        a1.setIdCliente("777");
        a1.setPeriodo("202301");
        a1.setConsumoM3(5);
        a1.setValorPagar(new BigDecimal("30.00"));

        FacturaAcueducto a2 = new FacturaAcueducto();
        a2.setIdCliente("777");
        a2.setPeriodo("202302");
        a2.setConsumoM3(7);
        a2.setValorPagar(new BigDecimal("40.00"));

        when(repo.findByIdCliente("777")).thenReturn(List.of(a1, a2));
        when(clienteService.obtenerNombrePorId("777")).thenReturn("Cliente 777");

        DeudaConsolidadaService service = new DeudaConsolidadaService(adapter, repo, clienteService);

        DeudaConsolidadaDTO dto = service.consultar("777");

        assertNotNull(dto);
        assertEquals("777", dto.getClienteId());
        assertEquals("Cliente 777", dto.getNombreCliente());
        // Debe seleccionar la factura de acueducto con periodo más reciente (202302)
        assertEquals("202302", dto.getAcueducto().getPeriodo());
        assertEquals(new BigDecimal("40.00"), dto.getAcueducto().getValorPagar());
        // Sin energía, el total debe ser igual al valor de acueducto
        assertEquals(new BigDecimal("40.00"), dto.getTotalAPagar());
    }
}
