package com.serviciudad.controller;

import com.serviciudad.dto.DeudaConsolidadaDTO;
import com.serviciudad.service.DeudaConsolidadaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DeudaConsolidadaControllerTest {

    @Test
    void getDeudaConsolidadaReturnsOkWhenServiceSucceeds() {
        DeudaConsolidadaService service = Mockito.mock(DeudaConsolidadaService.class);
        DeudaConsolidadaDTO dto = new DeudaConsolidadaDTO();
        dto.setClienteId("123");
        when(service.consultar("123")).thenReturn(dto);

        DeudaConsolidadaController controller = new DeudaConsolidadaController(service);
        ResponseEntity<DeudaConsolidadaDTO> resp = controller.getDeudaConsolidada("123");

        assertEquals(200, resp.getStatusCodeValue());
        assertNotNull(resp.getBody());
        assertEquals("123", resp.getBody().getClienteId());
    }

    @Test
    void getDeudaConsolidadaHandlesServiceException() {
        DeudaConsolidadaService service = Mockito.mock(DeudaConsolidadaService.class);
        when(service.consultar("bad")).thenThrow(new RuntimeException("boom"));

        DeudaConsolidadaController controller = new DeudaConsolidadaController(service);
        ResponseEntity<DeudaConsolidadaDTO> resp = controller.getDeudaConsolidada("bad");

        assertEquals(500, resp.getStatusCodeValue());
        assertNull(resp.getBody());
    }
}
