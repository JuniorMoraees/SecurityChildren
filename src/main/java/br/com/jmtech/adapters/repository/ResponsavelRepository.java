package br.com.jmtech.adapters.repository;

import br.com.jmtech.infrastructure.persistence.entity.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {


//    List<ResponsavelAluno> findByAlunosContaining(Aluno aluno);

    List<Responsavel> findByAlunos_AlunoId(Long alunoId);

//    Responsavel findByAlunos_AlunoId(Integer alunoId);

    Responsavel findByAlunosAlunoId(Long alunoId);

    Optional<Responsavel> findResponsavelAlunoByCpfAndIdIsNot(String cpf, Long id);

    Optional<Responsavel> findByCpf(String cpf);
}
