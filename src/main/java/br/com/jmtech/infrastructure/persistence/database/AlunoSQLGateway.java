package br.com.jmtech.infrastructure.persistence.database;


import br.com.jmtech.adapters.repository.QRCodeAlunoRepository;
import br.com.jmtech.adapters.repository.ResponsavelAlunoRepository;
import br.com.jmtech.application.dto.PageDTO;
import br.com.jmtech.application.dto.PaginatedAnswerDTO;
import br.com.jmtech.infrastructure.persistence.entity.Aluno;
import br.com.jmtech.adapters.exception.NotFoundException;
import br.com.jmtech.adapters.gateway.AlunoGateway;
import br.com.jmtech.adapters.repository.AlunoRepository;
import br.com.jmtech.infrastructure.persistence.entity.QRCodeAluno;
import br.com.jmtech.infrastructure.persistence.entity.ResponsavelAluno;
import br.com.jmtech.infrastructure.persistence.entity.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
@AllArgsConstructor
public class AlunoSQLGateway implements AlunoGateway {

    @PersistenceContext
    private final EntityManager manager;

    private final AlunoRepository repository;
    private final ResponsavelAlunoRepository responsavelAlunoRepository;
    private final AlunoRepository alunoRepository;

    private final QRCodeAlunoRepository qrCodeAlunoRepository;

    @Override
    public Aluno findByIdOrElseThrow(Long idAluno) {
        return repository.findById(idAluno)
                .orElseThrow(()-> new NotFoundException("Aluno não existe na base de dados"));
    }

    /*@Override
    public Aluno findByQrCodeOrElseThrow(String qrCode) {
        return repository.findByQrCode(qrCode)
                .orElseThrow(()-> new NotFoundException("QRCode inválido, ou não cadastrado"));
    }*/

    @Override
    public Aluno createAluno(Aluno aluno) {
        return repository.save(aluno);
    }


    @Override
    public PaginatedAnswerDTO<Aluno> findAll(String nome, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Aluno> pageResult;

        if ( nome !=null && !nome.trim().isEmpty()) {
            pageResult = repository.findByNomeContainingIgnoreCase(nome, pageable);
        }else {
            pageResult = repository.findAll(pageable);
        }

        PageDTO pageDTO = new PageDTO();
        pageDTO.setTotalRecords((int) pageResult.getTotalElements());
        pageDTO.setTotalPages(pageResult.getTotalPages());
        pageDTO.setPageNumber(page);
        pageDTO.setPageSize(pageSize);

        return PaginatedAnswerDTO.<Aluno>builder()
                .answerContent(pageResult.getContent())
                .pageMetaData(pageDTO)
                .build();
    }

    @Override
    public Aluno updateAluno(Aluno aluno) {
        manager.clear();
        return repository.save(aluno);
    }

    @Override
    public void delete(Long idAluno) {
        ResponsavelAluno aluDelete = responsavelAlunoRepository.findResponsavelAlunoByAluno_AlunoId(idAluno);
        if (aluDelete != null) {
            responsavelAlunoRepository.deleteById(aluDelete.getId());
        }
        List<QRCodeAluno> qrCodes = qrCodeAlunoRepository.findAllByAluno_AlunoId(idAluno);
        if (qrCodes != null && !qrCodes.isEmpty()) {
            for (QRCodeAluno qrCodeAluno : qrCodes) {
                qrCodeAlunoRepository.deleteById(qrCodeAluno.getId());
            }
        }
        repository.deleteById(idAluno);
    }

    @Override
    public List<Aluno> findBynome(String nome) {
        return repository.findByNome(nome);
    }
}
