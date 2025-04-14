package br.com.jmtech.infrastructure.data;


import br.com.jmtech.infrastructure.domains.Aluno;
import br.com.jmtech.interfaceAdapters.exception.NotFoundException;
import br.com.jmtech.interfaceAdapters.gateway.AlunoGateway;
import br.com.jmtech.interfaceAdapters.repositories.AlunoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@AllArgsConstructor
public class AlunoSQLGateway implements AlunoGateway {

    @PersistenceContext
    private final EntityManager manager;

    private final AlunoRepository repository;

    @Override
    public Aluno findByIdOrElseThrow(Long idAluno) {
        return repository.findById(idAluno)
                .orElseThrow(()-> new NotFoundException("Aluno não existe na base de dados"));
    }

    @Override
    public Aluno findByQrCodeOrElseThrow(String qrCode) {
        return repository.findByQrCode(qrCode)
                .orElseThrow(()-> new NotFoundException("QRCode inválido, ou não cadastrado"));
    }

    @Override
    public Aluno createAluno(Aluno aluno) {
        return repository.save(aluno);
    }

    @Override
    public List<Aluno> findAll() {
        return repository.findAll();
    }

    @Override
    public Aluno updateAluno(Aluno aluno) {
        manager.clear();
        return repository.save(aluno);
    }

    @Override
    public void delete(Long idAluno) {
        repository.deleteById(idAluno);
    }
}
