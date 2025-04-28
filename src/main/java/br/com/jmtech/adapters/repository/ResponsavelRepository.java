package br.com.jmtech.adapters.repository;

import br.com.jmtech.application.dto.responsavel.ResponsavelSearchDTO;
import br.com.jmtech.infrastructure.persistence.entity.Responsavel;
import br.com.jmtech.infrastructure.persistence.entity.ResponsavelAluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {


//    List<Responsavel> findByAlunos_AlunoId(Long aluno);

    @Query("SELECT ra.responsavel FROM ResponsavelAluno ra WHERE ra.aluno.alunoId = :alunoId AND ra.ativo = true")
    List<Responsavel> findResponsaveisAtivosByAlunoId(Long alunoId);

//    Responsavel findByAlunos_AlunoId(Integer alunoId);

    Responsavel findByAlunosAlunoId(Long alunoId);

    Optional<Responsavel> findResponsavelAlunoByCpfAndIdIsNot(String cpf, Long id);

    Optional<Responsavel> findByCpf(String cpf);

    List<Responsavel> findResponsavelsByNome(String nome);
}
