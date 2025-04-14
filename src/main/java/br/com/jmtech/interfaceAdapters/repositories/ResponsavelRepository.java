package br.com.jmtech.interfaceAdapters.repositories;

import br.com.jmtech.infrastructure.domains.Aluno;
import br.com.jmtech.infrastructure.domains.ResponsavelAluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResponsavelRepository extends JpaRepository<ResponsavelAluno, Long> {


    List<ResponsavelAluno> findByAlunosContaining(Aluno aluno);

    List<ResponsavelAluno> findByAlunosIdsContaining(Long alunoId);

    ResponsavelAluno findByAlunoId(Long alunoId);

    Optional<ResponsavelAluno> findResponsavelAlunoByCpfAndIdIsNot(String cpf, Long id);

    Optional<ResponsavelAluno> findByCpf(String cpf);
}
