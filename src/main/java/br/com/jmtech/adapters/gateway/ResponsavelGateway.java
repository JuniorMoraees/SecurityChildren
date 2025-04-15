package br.com.jmtech.adapters.gateway;

import br.com.jmtech.infrastructure.persistence.entity.ResponsavelAluno;

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
