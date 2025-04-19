package br.com.jmtech.domain.usecase;

import br.com.jmtech.application.assembler.ResponsavelAssembler;
import br.com.jmtech.application.dto.responsavel.ResponsavelAlunoDTO;
import br.com.jmtech.application.dto.responsavel.ResponsavelAlunoSearchDTO;
import br.com.jmtech.application.dto.responsavel.ResponsavelAlunoUpdateDTO;
import br.com.jmtech.application.dto.responsavel.ResponsavelAlunoCreateDTO;
import br.com.jmtech.infrastructure.persistence.entity.ResponsavelAluno;
import br.com.jmtech.adapters.exception.DataBaseCreateException;
import br.com.jmtech.adapters.gateway.ResponsavelGateway;
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

    public Long create(ResponsavelAlunoCreateDTO responsavelAlunoCreateDTO) throws DataBaseCreateException {
        ResponsavelAluno newResponsavel = responsavelAssembler.toResponsavel(responsavelAlunoCreateDTO);
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

    public ResponsavelAlunoSearchDTO findById(Long idResponsavel) {
        ResponsavelAluno responsavel = responsavelGateway.findByIdOrElseThrow(idResponsavel);
        return responsavelAssembler.toResponsavelSearchDTO(responsavel);
    }

    public void update(ResponsavelAlunoUpdateDTO responsavelAlunoUpdate, long idResponsavel) throws DataBaseCreateException {
        ResponsavelAluno responsavelToUpdate = mapToClient(responsavelAlunoUpdate, idResponsavel);
        manager.clear();
        isExistResponsavel(responsavelToUpdate);
        responsavelGateway.updateResponsavel(responsavelToUpdate);
    }

    private ResponsavelAluno mapToClient(ResponsavelAlunoUpdateDTO responsavelAlunoUpdate, long idResponsavel) {
        ResponsavelAluno existResponsavel = responsavelGateway.findByIdOrElseThrow(idResponsavel);
        return responsavelAssembler.toResponsavel(responsavelAlunoUpdate, existResponsavel, idResponsavel);
    }

    public void delete(long idResponsavel) {
        ResponsavelAluno responsavelForDelete = responsavelGateway.findByIdOrElseThrow(idResponsavel);
        responsavelGateway.delete(responsavelForDelete.getId());
    }
}
