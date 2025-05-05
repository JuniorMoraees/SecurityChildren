package br.com.jmtech.adapters.repository;

import br.com.jmtech.infrastructure.persistence.entity.Aluno;
import br.com.jmtech.infrastructure.persistence.entity.Responsavel;
import br.com.jmtech.infrastructure.persistence.entity.ResponsavelAluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponsavelAlunoRepository extends JpaRepository<ResponsavelAluno, Long> {

    boolean existsByResponsavelAndAluno(Responsavel responsavel, Aluno aluno);

    List<ResponsavelAluno> findByResponsavel(Responsavel responsavel);

    List<ResponsavelAluno> findByAluno(Aluno aluno);
}
