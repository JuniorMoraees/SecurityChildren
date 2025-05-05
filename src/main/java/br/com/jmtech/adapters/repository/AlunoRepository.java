package br.com.jmtech.adapters.repository;

import br.com.jmtech.infrastructure.persistence.entity.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

//    Optional<Aluno> findByQrCode(String qrCode);

    Page<Aluno> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

    @Query("SELECT a FROM Aluno a WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Aluno> findByNome(@Param("nome") String nome);
}
