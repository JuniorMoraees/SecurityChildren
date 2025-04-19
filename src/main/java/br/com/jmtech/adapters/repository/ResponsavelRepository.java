package br.com.jmtech.adapters.repository;

import br.com.jmtech.infrastructure.persistence.entity.ResponsavelAluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResponsavelRepository extends JpaRepository<ResponsavelAluno, Long> {


//    List<ResponsavelAluno> findByAlunosContaining(Aluno aluno);

    List<ResponsavelAluno> findByAlunos_AlunoId(Long alunoId);

    ResponsavelAluno findByAlunos_AlunoId(Integer alunoId);

    Optional<ResponsavelAluno> findResponsavelAlunoByCpfAndIdIsNot(String cpf, Long id);

    Optional<ResponsavelAluno> findByCpf(String cpf);
}
