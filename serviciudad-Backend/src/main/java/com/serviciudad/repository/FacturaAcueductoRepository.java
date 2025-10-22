package com.serviciudad.repository;

import com.serviciudad.domain.FacturaAcueducto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FacturaAcueductoRepository extends JpaRepository<FacturaAcueducto, Long> {
    List<FacturaAcueducto> findByIdCliente(String idCliente);
}
