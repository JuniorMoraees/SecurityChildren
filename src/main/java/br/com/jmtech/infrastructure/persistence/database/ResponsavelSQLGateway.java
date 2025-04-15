package br.com.jmtech.infrastructure.persistence.database;

import br.com.jmtech.infrastructure.persistence.entity.ResponsavelAluno;
import br.com.jmtech.adapters.gateway.ResponsavelGateway;
import br.com.jmtech.adapters.repository.ResponsavelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ResponsavelSQLGateway implements ResponsavelGateway {

    @PersistenceContext
    private final EntityManager manager;

    private final ResponsavelRepository responsavelRepository;

    @Override
    public ResponsavelAluno findByAlunoId(Long alunoId) {
        return responsavelRepository.findByAlunoId(alunoId);
    }

    @Override
    public ResponsavelAluno createResponsavel(ResponsavelAluno newResponsavel) {
        return responsavelRepository.save(newResponsavel);
    }

    @Override
    public Optional<ResponsavelAluno> findResponsavelAlunoByCpfAndIdIsNot(String cpf, Long idResponsavel) {
        return responsavelRepository.findResponsavelAlunoByCpfAndIdIsNot(cpf, idResponsavel);
    }

    @Override
    public Optional<ResponsavelAluno> findByCpf(String cpf) {
        return responsavelRepository.findByCpf(cpf);
    }

    @Override
    public List<ResponsavelAluno> findAll() {
        return responsavelRepository.findAll();
    }

    @Override
    public ResponsavelAluno findByIdOrElseThrow(Long idResponsavel) {
        return responsavelRepository.findById(idResponsavel)
                .orElseThrow(() -> new RuntimeException("Responsavel não existe na base de dados"));
    }

    @Override
    public ResponsavelAluno updateResponsavel(ResponsavelAluno responsavel) {
        manager.clear();
        return responsavelRepository.save(responsavel);
    }

    @Override
    public void delete(Long id) {
        responsavelRepository.deleteById(id);
    }
}

