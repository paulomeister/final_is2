package com.serviciudad.adapter;

import com.serviciudad.domain.FacturaEnergia;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdaptadorArchivoEnergiaTest {

    @Test
    void leerFacturasParsesFixedWidthFileFromClasspath() {
        // Usamos el recurso de pruebas src/test/resources/consumos_energia.txt
        AdaptadorArchivoEnergia adapter = new AdaptadorArchivoEnergia("/consumos_energia.txt");
        List<FacturaEnergia> lista = adapter.leerFacturas();

        assertNotNull(lista, "La lista no debe ser null");
        assertEquals(2, lista.size(), "Se esperaban exactamente 2 registros en el recurso de prueba");

        FacturaEnergia f1 = lista.get(0);
        assertEquals("1234500001", f1.getIdCliente());
        assertEquals("202301", f1.getPeriodo());
        assertEquals(123, f1.getConsumoKwh());
        assertEquals(new BigDecimal("100.50"), f1.getValorPagar());

        FacturaEnergia f2 = lista.get(1);
        assertEquals("1234500002", f2.getIdCliente());
        assertEquals("202302", f2.getPeriodo());
        assertEquals(45, f2.getConsumoKwh());
        assertEquals(new BigDecimal("50.75"), f2.getValorPagar());
    }
}
