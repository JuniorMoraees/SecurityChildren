package br.com.jmtech.adapters.repository;

import br.com.jmtech.application.dto.aluno.AlunoSearchDTO;
import br.com.jmtech.infrastructure.persistence.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Optional<Aluno> findByQrCode(String qrCode);

    List<Aluno> findAlunosByNome(String nome);
}
