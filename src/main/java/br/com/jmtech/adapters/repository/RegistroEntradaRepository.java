package br.com.jmtech.adapters.repository;

import br.com.jmtech.infrastructure.persistence.entity.RegistroEntrada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroEntradaRepository extends JpaRepository<RegistroEntrada, Long> {
}
