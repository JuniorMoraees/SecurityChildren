package br.com.jmtech.application.usecase;

import br.com.jmtech.application.assembler.ResponsavelAssembler;
import br.com.jmtech.application.dto.aluno.AlunoDTO;
import br.com.jmtech.application.dto.responsavel.ResponsavelAlunoDTO;
import br.com.jmtech.application.dto.responsavel.ResponsavelCreateDTO;
import br.com.jmtech.infrastructure.domains.ResponsavelAluno;
import br.com.jmtech.interfaceAdapters.exception.DataBaseCreateException;
import br.com.jmtech.interfaceAdapters.gateway.ResponsavelGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ResponsavelUseCase {

    private final ResponsavelGateway responsavelGateway;
    private final ResponsavelAssembler responsavelAssembler;

    @PersistenceContext
    private final EntityManager manager;

    public Long create(ResponsavelCreateDTO responsavelCreateDTO) throws DataBaseCreateException {
        ResponsavelAluno newResponsavel = responsavelAssembler.toResponsavel(responsavelCreateDTO);
        isExistResponsavel(newResponsavel);
        return responsavelGateway.createResponsavel(newResponsavel).getId();
    }

    public List<ResponsavelAlunoDTO> findAll() {
        return responsavelAssembler.toResponsavelDTO(responsavelGateway.findAll());
    }


    public void isExistResponsavel(ResponsavelAluno responsavel) throws DataBaseCreateException {
        Optional<ResponsavelAluno> responsavelDb;
        if (responsavel.getCpf() != null) {
            if (responsavel.getId() != null ) {
                responsavelDb = responsavelGateway.findResponsavelAlunoByCpfAndIdIsNot(responsavel.getCpf(), responsavel.getId());
            }else {
                responsavelDb = responsavelGateway.findByCpf(responsavel.getCpf());
            }
            if (responsavelDb.isPresent()) {
                throw new DataBaseCreateException("Responsavel de cpf: " + responsavel.getCpf() + " já cadastrado no sistema");
            }
        }
    }

}
