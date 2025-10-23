package com.serviciudad;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class ConsumosEnergiaFileTest {

    private static final Path FILE = Paths.get("src/main/resources/consumos_energia.txt");
    private static final Pattern LINE_PATTERN = Pattern.compile("^\\d{10}\\d{6}\\d{8}\\d{9}\\.\\d{2}$");

    // IDs que deben aparecer (según el archivo)
    private static final List<String> EXPECTED_IDS = List.of(
            "1234500002",
            "1234500003",
            "1234500006",
            "1234500007",
            "1234500009",
            "1234500008",
            "1234500001",
            "1234500004",
            "1234500000",
            "1234500005"
    );

    // IDs a las que se les agregaron 2 periodos adicionales (deberían tener 3 registros cada una)
    private static final Set<String> IDS_WITH_EXTRA = Set.of(
            "1234500002",
            "1234500003",
            "1234500006",
            "1234500001"
    );

    @Test
    public void archivoTieneFormatoValidoYRegistrosEsperados() throws Exception {
        assertTrue(Files.exists(FILE), "El archivo consumos_energia.txt debe existir en src/main/resources");

        List<String> lines = Files.readAllLines(FILE).stream()
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .filter(s -> !s.startsWith("//"))
                .collect(Collectors.toList());

        assertFalse(lines.isEmpty(), "No hay líneas de datos en el archivo.");

        // Validar formato y agrupar por id
        Map<String, List<String>> byId = new HashMap<>();
        for (String line : lines) {
            assertTrue(LINE_PATTERN.matcher(line).matches(), "Línea con formato inválido: " + line);
            String id = line.substring(0, 10);
            String periodo = line.substring(10, 16);
            String consumo = line.substring(16, 24);
            String valor = line.substring(24);
            // Comprobaciones mínimas de contenido
            assertEquals(36, line.length(), "Longitud incorrecta en línea: " + line);
            assertEquals(6, periodo.length());
            assertEquals(8, consumo.length());
            assertTrue(valor.contains("."), "Valor debe contener punto decimal: " + line);

            byId.computeIfAbsent(id, k -> new ArrayList<>()).add(periodo);
        }

        // Verificar que todos los IDs esperados aparecen
        for (String expectedId : EXPECTED_IDS) {
            assertTrue(byId.containsKey(expectedId), "Falta ID en los registros: " + expectedId);
            // Debe tener al menos un registro para 202510
            List<String> periods = byId.get(expectedId);
            assertTrue(periods.contains("202510"), "El ID " + expectedId + " no tiene registro para periodo 202510");
        }

        // Verificar que los IDs seleccionados tienen exactamente 3 registros
        for (String id : IDS_WITH_EXTRA) {
            List<String> periods = byId.get(id);
            assertNotNull(periods, "ID con extras no encontrado: " + id);
            assertEquals(3, periods.size(), "Se esperaba 3 registros para el ID " + id + ", se encontró: " + periods.size());
        }

        // Verificación adicional: al menos 10 ids distintos
        assertTrue(byId.keySet().size() >= 10, "Se esperaba al menos 10 IDs distintos en el archivo");
    }
}

