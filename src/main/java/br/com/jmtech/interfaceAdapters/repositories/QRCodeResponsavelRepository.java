package br.com.jmtech.interfaceAdapters.repositories;

import br.com.jmtech.infrastructure.domains.Aluno;
import br.com.jmtech.infrastructure.domains.QRCodeResponsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QRCodeResponsavelRepository extends JpaRepository<QRCodeResponsavel, Long > {

    void deleteByAluno(Aluno aluno);

}
