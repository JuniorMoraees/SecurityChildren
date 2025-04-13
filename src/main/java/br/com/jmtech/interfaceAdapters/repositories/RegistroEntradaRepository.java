package br.com.jmtech.interfaceAdapters.repositories;

import br.com.jmtech.infrastructure.domains.RegistroEntrada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroEntradaRepository extends JpaRepository<RegistroEntrada, Long> {
}
