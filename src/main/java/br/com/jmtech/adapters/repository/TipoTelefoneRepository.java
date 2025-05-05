package br.com.jmtech.adapters.repository;

import br.com.jmtech.infrastructure.persistence.entity.TipoTelefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoTelefoneRepository extends JpaRepository<TipoTelefone, Long> {
}
