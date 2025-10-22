package com.serviciudad.service;

import com.serviciudad.adapter.AdaptadorArchivoEnergia;
import com.serviciudad.domain.FacturaAcueducto;
import com.serviciudad.domain.FacturaEnergia;
import com.serviciudad.dto.DeudaConsolidadaDTO;
import com.serviciudad.repository.FacturaAcueductoRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class DeudaConsolidadaService {

    private final AdaptadorArchivoEnergia adaptador;
    private final FacturaAcueductoRepository repo;
    private final ClienteService clienteService;

    public DeudaConsolidadaService(AdaptadorArchivoEnergia adaptador, FacturaAcueductoRepository repo, ClienteService clienteService) {
        this.adaptador = adaptador;
        this.repo = repo;
        this.clienteService = clienteService;
    }

    public DeudaConsolidadaDTO consultar(String clienteId) {
        // 1) energía (archivo)
        List<FacturaEnergia> energias = adaptador.leerFacturas();
        FacturaEnergia fe = energias.stream()
                .filter(f -> f.getIdCliente() != null && f.getIdCliente().equals(clienteId))
                .findFirst()
                .orElse(null);

        // 2) acueducto (BD) -> tomamos la factura más reciente por periodo (si hay varias)
        List<FacturaAcueducto> acs = repo.findByIdCliente(clienteId);
        FacturaAcueducto fa = acs.stream()
                .max(Comparator.comparing(FacturaAcueducto::getPeriodo))
                .orElse(null);

        // 3) build DTO
        DeudaConsolidadaBuilder builder = new DeudaConsolidadaBuilder()
                .withClienteId(clienteId)
                .withNombre(clienteService.obtenerNombrePorId(clienteId))
                .withFecha(OffsetDateTime.now());

        if (fe != null) {
            builder.withEnergia(fe.getPeriodo(), fe.getConsumoKwh() + " kWh", fe.getValorPagar());
        } else {
            builder.withEnergia("-", "0 kWh", null);
        }

        if (fa != null) {
            builder.withAcueducto(fa.getPeriodo(), fa.getConsumoM3() + " m³", fa.getValorPagar());
        } else {
            builder.withAcueducto("-", "0 m³", null);
        }

        return builder.build();
    }
}
