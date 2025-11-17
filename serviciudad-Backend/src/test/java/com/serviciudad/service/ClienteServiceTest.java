package com.serviciudad.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteServiceTest {

    @Test
    void knownClientReturnsName() {
        ClienteService s = new ClienteService();
        String name = s.obtenerNombrePorId("1234500002");
        assertEquals("Andrés Martínez", name);
    }

    @Test
    void unknownClientReturnsId() {
        ClienteService s = new ClienteService();
        String id = "no-existe";
        String res = s.obtenerNombrePorId(id);
        assertEquals(id, res);
    }
}
