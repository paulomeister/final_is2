package com.serviciudad;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class ConsumosEnergiaFileTest {

    private static final Pattern LINE_PATTERN = Pattern.compile("^\\d{10}\\d{6}\\d{8}\\d+\\.\\d{2}$");

    @Test
    public void archivoTieneLineasConFormatoValido() throws Exception {
        // Cargar como recurso en el classpath para evitar dependencia a estructura de carpetas
        try (var is = getClass().getResourceAsStream("/consumos_energia.txt")) {
            assertNotNull(is, "Recurso consumos_energia.txt no encontrado en el classpath");
            try (var br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                List<String> lines = br.lines()
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .filter(s -> !s.startsWith("//"))
                        .collect(Collectors.toList());

                assertFalse(lines.isEmpty(), "El recurso debe contener al menos una línea de datos");

                for (String line : lines) {
                    assertTrue(LINE_PATTERN.matcher(line).matches(), "Formato inválido en línea: " + line);
                }
            }
        }
    }
}

