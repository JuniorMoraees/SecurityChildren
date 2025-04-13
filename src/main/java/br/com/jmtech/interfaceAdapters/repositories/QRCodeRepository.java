package br.com.jmtech.interfaceAdapters.repositories;

import br.com.jmtech.infrastructure.domains.Aluno;
import br.com.jmtech.infrastructure.domains.QRCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QRCodeRepository extends JpaRepository<QRCode, Long> {

    QRCode findByAluno(Aluno aluno);

    Optional<QRCode> findByCodigoQR(String codigoQR);
}
