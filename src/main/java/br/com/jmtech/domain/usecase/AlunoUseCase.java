package br.com.jmtech.domain.usecase;


import br.com.jmtech.application.assembler.AlunoAssembler;
import br.com.jmtech.application.dto.aluno.AlunoCreateDTO;
import br.com.jmtech.application.dto.aluno.AlunoDTO;
import br.com.jmtech.application.dto.aluno.AlunoSearchDTO;
import br.com.jmtech.application.dto.aluno.AlunoUpdateDTO;
import br.com.jmtech.application.services.QrCodeReader;
import br.com.jmtech.infrastructure.persistence.entity.Aluno;
import br.com.jmtech.infrastructure.persistence.entity.ResponsavelAluno;
import br.com.jmtech.adapters.gateway.AlunoGateway;
import br.com.jmtech.adapters.gateway.ResponsavelGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;

@Service
@AllArgsConstructor
public class AlunoUseCase {

    private final AlunoGateway alunoGateway;
    private final AlunoAssembler alunoAssembler;
    private final QrCodeReader qrCodeReader;
    private final ResponsavelGateway responsavelGateway;

    @PersistenceContext
    private final EntityManager manager;

    public AlunoDTO findAlunoByQRCode(String qrCode) {
        Aluno aluno = qrCodeReader.leitorQrCode(qrCode);
        ResponsavelAluno responsavel = responsavelGateway.findByAlunoId(aluno.getIdAluno());
        return alunoAssembler.toAlunoDTO(aluno, responsavel);
    }

    public Long create(@Valid AlunoCreateDTO alunoDTO) {
        Aluno aluno = alunoAssembler.toAluno(alunoDTO);
//        isExisteAluno(aluno);
        return alunoGateway.createAluno(aluno).getIdAluno();
    }

    public List<AlunoDTO> findAll() {
        return alunoAssembler.toAlunoDTO(alunoGateway.findAll());
    }

    public AlunoSearchDTO findById(Long idAluno) {
        Aluno aluno = alunoGateway.findByIdOrElseThrow(idAluno);
        return alunoAssembler.toAlunoSearchDTO(aluno);
    }

    public void update(@Valid AlunoUpdateDTO alunoUpdate, Long idAluno) {
        Aluno alunoToUpdate = mapToAluno(alunoUpdate, idAluno);
        manager.clear();
        alunoGateway.updateAluno(alunoToUpdate);
    }


    private Aluno mapToAluno(AlunoUpdateDTO alunoUpdateDTO, Long idAluno) {
        Aluno alunoExist = alunoGateway.findByIdOrElseThrow(idAluno);
        return alunoAssembler.toAluno(alunoUpdateDTO,alunoExist, idAluno);
    }

    public void delete(Long idAluno) {
        Aluno alunoForDelete = alunoGateway.findByIdOrElseThrow(idAluno);
        alunoGateway.delete(alunoForDelete.getIdAluno());
    }
}
