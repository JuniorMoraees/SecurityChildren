package br.com.jmtech.interfaceAdapters.gateway;

import br.com.jmtech.infrastructure.domains.ResponsavelAluno;

public interface ResponsavelGateway {

    ResponsavelAluno findByAlunoId(Long alunoId);
}
