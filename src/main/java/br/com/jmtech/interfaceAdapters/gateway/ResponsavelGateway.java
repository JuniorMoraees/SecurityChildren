package br.com.jmtech.interfaceAdapters.gateway;

import br.com.jmtech.infrastructure.domains.ResponsavelAluno;

import java.util.List;
import java.util.Optional;

public interface ResponsavelGateway {

    ResponsavelAluno findByAlunoId(Long alunoId);

    ResponsavelAluno createResponsavel(ResponsavelAluno newResponsavel);

    Optional<ResponsavelAluno> findResponsavelAlunoByCpfAndIdIsNot(String cpf, Long id);

    Optional<ResponsavelAluno> findByCpf(String cpf);

    List<ResponsavelAluno> findAll();

    ResponsavelAluno findByIdOrElseThrow(Long idResponsavel);

    ResponsavelAluno updateResponsavel(ResponsavelAluno responsavelToUpdate);

    void delete(Long id);
}
