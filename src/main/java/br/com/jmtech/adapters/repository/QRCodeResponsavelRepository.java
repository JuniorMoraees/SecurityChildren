package br.com.jmtech.adapters.repository;

import br.com.jmtech.infrastructure.persistence.entity.Aluno;
import br.com.jmtech.infrastructure.persistence.entity.QRCodeResponsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QRCodeResponsavelRepository extends JpaRepository<QRCodeResponsavel, Long > {

    void deleteByAluno(Aluno aluno);

}
