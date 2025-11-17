package com.serviciudad.adapter;

import com.serviciudad.domain.FacturaEnergia;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdaptadorArchivoEnergiaErrorCasesTest {

    @Test
    void parseHandlesMalformedLinesGracefully() {
        // Creamos un InputStream sintético con líneas mal formadas y una correcta
        String contenido = String.join("\n",
                "shortline", // demasiado corta
                "1234500003202303ABCDEFGnotanumber", // consumo y valor inválidos
                "123450000120230100000123100.50" // línea válida
        );
        var is = new java.io.ByteArrayInputStream(contenido.getBytes(StandardCharsets.UTF_8));

        AdaptadorArchivoEnergia adapter = new AdaptadorArchivoEnergia();
        List<FacturaEnergia> lista = adapter.leerFacturas(is);

        assertNotNull(lista);
        assertEquals(3, lista.size());

        // Primera línea: datos incompletos => consumo y valor 0
        FacturaEnergia l1 = lista.get(0);
        assertEquals(0, l1.getConsumoKwh());
        assertEquals(java.math.BigDecimal.ZERO, l1.getValorPagar());

        // Segunda línea: parseo falla => valores por defecto
        FacturaEnergia l2 = lista.get(1);
        assertEquals(0, l2.getConsumoKwh());
        assertEquals(java.math.BigDecimal.ZERO, l2.getValorPagar());

        // Tercera línea: correcta
        FacturaEnergia l3 = lista.get(2);
        assertEquals("1234500001", l3.getIdCliente());
        assertEquals("202301", l3.getPeriodo());
        assertEquals(123, l3.getConsumoKwh());
        assertEquals(new java.math.BigDecimal("100.50"), l3.getValorPagar());
    }

    @Test
    void leerFacturasReturnsEmptyListWhenResourceMissing() {
        AdaptadorArchivoEnergia adapter = new AdaptadorArchivoEnergia("/no-existe-archivo.txt");
        java.util.List<FacturaEnergia> lista = adapter.leerFacturas();

        assertNotNull(lista);
        assertTrue(lista.isEmpty(), "Cuando el recurso no existe debe devolverse una lista vacía");
    }
}
