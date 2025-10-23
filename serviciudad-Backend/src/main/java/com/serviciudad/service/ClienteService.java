package com.serviciudad.service;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Mock simple para obtener nombre de cliente. En un escenario real consultaría otro sistema.
 */
@Service
public class ClienteService {

    private static final Map<String, String> CLIENTES = Map.of(
            "1234500002", "Andrés Martínez",
            "1234500003", "Lucía Fernández",
            "1234500006", "Diego Ramírez",
            "1234500007", "Sofía López",
            "1234500009", "Mariana Torres",
            "1234500008", "Pablo Gómez",
            "1234500001", "Valeria Jiménez",
            "1234500004", "Ricardo Silva",
            "1234500000", "Natalia Cruz",
            "1234500005", "Esteban Rojas"
    );

    public String obtenerNombrePorId(String clienteId) {
        return CLIENTES.getOrDefault(clienteId, clienteId);
    }
}
