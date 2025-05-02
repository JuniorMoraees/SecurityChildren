package br.com.jmtech.infrastructure.persistence.database;

import br.com.jmtech.application.dto.PageDTO;
import br.com.jmtech.application.dto.PaginatedAnswerDTO;
import br.com.jmtech.infrastructure.persistence.entity.Responsavel;
import br.com.jmtech.adapters.gateway.ResponsavelGateway;
import br.com.jmtech.adapters.repository.ResponsavelRepository;
import br.com.jmtech.infrastructure.persistence.entity.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

//    @Override
//    public Responsavel findByAlunoId(Integer alunoId) {
//        return responsavelRepository.findByAlunos_AlunoId(alunoId);
//    }

    @Override
    public Responsavel findByAlunoId(Long alunoId) {
        return responsavelRepository.findByAlunosAlunoId(alunoId);
    }

    @Override
    public Responsavel createResponsavel(Responsavel newResponsavel) {
        return responsavelRepository.save(newResponsavel);
    }

    @Override
    public Optional<Responsavel> findResponsavelAlunoByCpfAndIdIsNot(String cpf, Long idResponsavel) {
        return responsavelRepository.findResponsavelAlunoByCpfAndIdIsNot(cpf, idResponsavel);
    }

    @Override
    public Optional<Responsavel> findByCpf(String cpf) {
        return responsavelRepository.findByCpf(cpf);
    }

    @Override
    public PaginatedAnswerDTO<Responsavel> findAll(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize); // Spring usa page 0-based
        Page<Responsavel> pageResult = responsavelRepository.findAll(pageable);

        PageDTO pageDTO = new PageDTO();
        pageDTO.setTotalRecords((int) pageResult.getTotalElements());
        pageDTO.setTotalPages(pageResult.getTotalPages());
        pageDTO.setPageNumber(page);
        pageDTO.setPageSize(pageSize);

        return PaginatedAnswerDTO.<Responsavel>builder()
                .answerContent(pageResult.getContent())
                .pageMetaData(pageDTO)
                .build();
    }

    @Override
    public Responsavel findByIdOrElseThrow(Long idResponsavel) {
        return responsavelRepository.findById(idResponsavel)
                .orElseThrow(() -> new RuntimeException("Responsavel não existe na base de dados"));
    }

    @Override
    public Responsavel updateResponsavel(Responsavel responsavel) {
        manager.clear();
        return responsavelRepository.save(responsavel);
    }

    @Override
    public void delete(Long id) {
        responsavelRepository.deleteById(id);
    }
}

