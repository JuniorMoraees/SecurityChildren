package br.com.jmtech.infrastructure.data;

import br.com.jmtech.infrastructure.domains.ResponsavelAluno;
import br.com.jmtech.interfaceAdapters.gateway.ResponsavelGateway;
import br.com.jmtech.interfaceAdapters.repositories.ResponsavelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ResponsavelSQLGateway implements ResponsavelGateway {

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
}

