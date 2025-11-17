package com.serviciudad.adapter;

import com.serviciudad.domain.FacturaEnergia;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Lee el recurso consumos_energia.txt (en resources) con formato ancho-fijo:
 * id_cliente(10), periodo(6), consumo_kwh(8), valor_pagar(12+decimales)
 */
@Component
public class AdaptadorArchivoEnergia {

    /** Ruta por defecto en el classpath */
    private final String resourcePath;

    /** Constructor usado por Spring */
    public AdaptadorArchivoEnergia() { this("/consumos_energia.txt"); }

    /** Constructor alterno para pruebas, permite inyectar un recurso distinto */
    public AdaptadorArchivoEnergia(String resourcePath) { this.resourcePath = resourcePath; }

    /**
     * Lee el archivo de recursos por la ruta configurada.
     */
    public List<FacturaEnergia> leerFacturas() {
        return leerFacturas(null);
    }

    /**
     * Variante que acepta un InputStream externo (útil en pruebas). Si el stream es null
     * se intenta cargar el recurso del classpath.
     */
    public List<FacturaEnergia> leerFacturas(InputStream overrideStream) {
        List<FacturaEnergia> lista = new ArrayList<>();
        InputStream is = overrideStream != null ? overrideStream : this.getClass().getResourceAsStream(resourcePath);
        if (is == null) return lista;
        try (InputStream autoClose = is; // garantiza cierre también para streams externos
             BufferedReader br = new BufferedReader(new InputStreamReader(autoClose, StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                // defensivo: proteger contra longitudes menores
                String id = safeSubstring(line, 0, 10).trim();
                String periodo = safeSubstring(line, 10, 16).trim();
                String consumoStr = safeSubstring(line, 16, 24).trim();
                String valorStr = line.length() > 24 ? line.substring(24).trim() : "0";
                int consumo = parseLeadingInt(consumoStr);
                BigDecimal valor = parseDecimal(valorStr);
                FacturaEnergia f = new FacturaEnergia();
                f.setIdCliente(id);
                f.setPeriodo(periodo);
                f.setConsumoKwh(consumo);
                f.setValorPagar(valor);
                lista.add(f);
            }
        } catch (Exception e) {
            // log si se desea
            e.printStackTrace();
        }
        return lista;
    }

    private String safeSubstring(String s, int start, int end) {
        if (s.length() <= start) return "";
        return s.substring(start, Math.min(end, s.length()));
    }

    private int parseLeadingInt(String s) {
        if (s == null || s.isBlank()) return 0;
        s = s.replaceFirst("^0+", "");
        if (s.isBlank()) return 0;
        try { return Integer.parseInt(s); } catch (Exception e) { return 0; }
    }

    private BigDecimal parseDecimal(String s) {
        if (s == null || s.isBlank()) return BigDecimal.ZERO;
        try { return new BigDecimal(s); } catch (Exception e) { return BigDecimal.ZERO; }
    }
}
