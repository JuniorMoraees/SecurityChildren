package br.com.jmtech.interfaceAdapters.repositories;

import br.com.jmtech.infrastructure.domains.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Optional<Aluno> findByQrCode(String qrCode);
}
