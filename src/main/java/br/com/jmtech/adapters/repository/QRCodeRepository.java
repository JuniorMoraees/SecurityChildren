package br.com.jmtech.adapters.repository;

import br.com.jmtech.infrastructure.persistence.entity.Aluno;
import br.com.jmtech.infrastructure.persistence.entity.QRCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QRCodeRepository extends JpaRepository<QRCode, Long> {

    QRCode findByAluno(Aluno aluno);

    Optional<QRCode> findByCodigoQR(String codigoQR);
}
