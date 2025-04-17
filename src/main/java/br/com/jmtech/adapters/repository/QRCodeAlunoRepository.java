package br.com.jmtech.adapters.repository;

import br.com.jmtech.infrastructure.persistence.entity.QRCodeAluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QRCodeAlunoRepository extends JpaRepository<QRCodeAluno, Long> {
}
