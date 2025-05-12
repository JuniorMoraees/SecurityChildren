package br.com.jmtech.adapters.repository;

import br.com.jmtech.infrastructure.persistence.entity.Aluno;
import br.com.jmtech.infrastructure.persistence.entity.QRCodeResponsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QRCodeResponsavelRepository extends JpaRepository<QRCodeResponsavel, Long > {

    void deleteByAluno(Aluno aluno);

    @Query("SELECT DISTINCT qcr FROM QRCodeResponsavel qcr WHERE qcr.codigoQR = ?1")
    Optional<QRCodeResponsavel> findByCodigoQR(String codigoQR);

    List<QRCodeResponsavel> findAllByResponsavel_Id(Long responsavelId);
}
