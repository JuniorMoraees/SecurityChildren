package br.com.jmtech.domain.usecase;

import br.com.jmtech.application.assembler.ResponsavelAssembler;
import br.com.jmtech.application.dto.aluno.AlunoSearchDTO;
import br.com.jmtech.application.dto.responsavel.ResponsavelDTO;
import br.com.jmtech.application.dto.responsavel.ResponsavelSearchDTO;
import br.com.jmtech.application.dto.responsavel.ResponsavelUpdateDTO;
import br.com.jmtech.application.dto.responsavel.ResponsavelCreateDTO;
import br.com.jmtech.infrastructure.persistence.entity.Responsavel;
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

    public Long create(ResponsavelCreateDTO responsavelCreateDTO) throws DataBaseCreateException {
        Responsavel newResponsavel = responsavelAssembler.toResponsavel(responsavelCreateDTO);
        isExistResponsavel(newResponsavel);
        return responsavelGateway.createResponsavel(newResponsavel).getId();
    }

    public List<ResponsavelDTO> findAll() {
        return responsavelAssembler.toResponsavelDTO(responsavelGateway.findAll());
    }


    public void isExistResponsavel(Responsavel responsavel) throws DataBaseCreateException {
        Optional<Responsavel> responsavelDb;
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

    public ResponsavelSearchDTO findById(Long idResponsavel) {
        Responsavel responsavel = responsavelGateway.findByIdOrElseThrow(idResponsavel);
        return responsavelAssembler.toResponsavelSearchDTO(responsavel);
    }

    public void update(ResponsavelUpdateDTO responsavelAlunoUpdate, long idResponsavel) throws DataBaseCreateException {
        Responsavel responsavelToUpdate = mapToClient(responsavelAlunoUpdate, idResponsavel);
        manager.clear();
        isExistResponsavel(responsavelToUpdate);
        responsavelGateway.updateResponsavel(responsavelToUpdate);
    }

    private Responsavel mapToClient(ResponsavelUpdateDTO responsavelAlunoUpdate, long idResponsavel) {
        Responsavel existResponsavel = responsavelGateway.findByIdOrElseThrow(idResponsavel);
        return responsavelAssembler.toResponsavel(responsavelAlunoUpdate, existResponsavel, idResponsavel);
    }

    public void delete(long idResponsavel) {
        Responsavel responsavelForDelete = responsavelGateway.findByIdOrElseThrow(idResponsavel);
        responsavelGateway.delete(responsavelForDelete.getId());
    }

    public List<ResponsavelDTO> findByNome(String nome) {
        return responsavelAssembler.toResponsavelDTO(responsavelGateway.findByName(nome));
    }
}
