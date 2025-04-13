package br.com.jmtech.infrastructure.data;


import br.com.jmtech.infrastructure.domains.Aluno;
import br.com.jmtech.interfaceAdapters.exception.NotFoundException;
import br.com.jmtech.interfaceAdapters.gateway.AlunoGateway;
import br.com.jmtech.interfaceAdapters.repositories.AlunoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@AllArgsConstructor
public class AlunoSQLGateway implements AlunoGateway {

    @PersistenceContext
    private final EntityManager manager;

    private final AlunoRepository repository;

    @Override
    public Aluno findByIdOrElseThrow(Long idAluno) {
        return null;
    }

    @Override
    public Aluno findByQrCodeOrElseThrow(String qrCode) {
        return repository.findByQrCode(qrCode)
                .orElseThrow(()-> new NotFoundException("QRCode inválido, ou não cadastrado"));
    }
}
