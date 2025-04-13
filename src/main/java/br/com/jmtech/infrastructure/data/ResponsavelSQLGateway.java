package br.com.jmtech.infrastructure.data;

import br.com.jmtech.infrastructure.domains.ResponsavelAluno;
import br.com.jmtech.interfaceAdapters.gateway.ResponsavelGateway;
import br.com.jmtech.interfaceAdapters.repositories.ResponsavelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ResponsavelSQLGateway implements ResponsavelGateway {

    private final ResponsavelRepository responsavelRepository;

    @Override
    public ResponsavelAluno findByAlunoId(Long alunoId) {
        return responsavelRepository.findByAlunoId(alunoId);
    }
}

