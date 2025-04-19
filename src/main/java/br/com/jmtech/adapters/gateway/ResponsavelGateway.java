package br.com.jmtech.adapters.gateway;

import br.com.jmtech.infrastructure.persistence.entity.Responsavel;

import java.util.List;
import java.util.Optional;

public interface ResponsavelGateway {

//    Responsavel findByAlunoId(Integer alunoId);

    Responsavel findByAlunoId(Long alunoId);

    Responsavel createResponsavel(Responsavel newResponsavel);

    Optional<Responsavel> findResponsavelAlunoByCpfAndIdIsNot(String cpf, Long id);

    Optional<Responsavel> findByCpf(String cpf);

    List<Responsavel> findAll();

    Responsavel findByIdOrElseThrow(Long idResponsavel);

    Responsavel updateResponsavel(Responsavel responsavelToUpdate);

    void delete(Long id);
}
