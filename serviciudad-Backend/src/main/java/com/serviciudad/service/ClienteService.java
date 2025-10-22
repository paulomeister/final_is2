package com.serviciudad.service;

import org.springframework.stereotype.Service;

/**
 * Mock simple para obtener nombre de cliente. En un escenario real consultaría otro sistema.
 */
@Service
public class ClienteService {
    public String obtenerNombrePorId(String clienteId) {
        // mock fijo (puedes mejorar esto)
        return "Juan Pérez";
    }
}
