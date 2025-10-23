package com.serviciudad.controller;

import com.serviciudad.dto.DeudaConsolidadaDTO;
import com.serviciudad.service.DeudaConsolidadaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clientes")
@CrossOrigin(origins = "*")
public class DeudaConsolidadaController {

    private final DeudaConsolidadaService service;

    public DeudaConsolidadaController(DeudaConsolidadaService service) {
        this.service = service;
    }

    @GetMapping("/{clienteId}/deuda-consolidada")
    public ResponseEntity<DeudaConsolidadaDTO> getDeudaConsolidada(@PathVariable String clienteId) {
        try {
            DeudaConsolidadaDTO dto = service.consultar(clienteId);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
