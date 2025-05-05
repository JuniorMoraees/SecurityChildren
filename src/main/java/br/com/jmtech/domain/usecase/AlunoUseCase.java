package br.com.jmtech.domain.usecase;


import br.com.jmtech.application.assembler.AlunoAssembler;
import br.com.jmtech.application.dto.PaginatedAnswerDTO;
import br.com.jmtech.application.dto.aluno.*;
import br.com.jmtech.application.services.QrCodeReader;
import br.com.jmtech.infrastructure.persistence.entity.Aluno;
import br.com.jmtech.infrastructure.persistence.entity.QRCodeResponsavel;
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

    public AlunoResponsavelDTO findAlunoByQRCode(String qrCode) {
        QRCodeResponsavel codigo = qrCodeReader.leitorQrCode(qrCode);
        return alunoAssembler.toAlunoResponsavelDTO(codigo.getAluno(), codigo.getResponsavel());
    }

    public Long create(@Valid AlunoCreateDTO alunoDTO) {
        Aluno aluno = alunoAssembler.toAluno(alunoDTO);
//        isExisteAluno(aluno);
        return alunoGateway.createAluno(aluno).getAlunoId();
    }

    public PaginatedAnswerDTO<AlunoDTO> findAll(String nome, Integer page, Integer pageSize) {
        return alunoAssembler.toAlunoDTO(alunoGateway.findAll(nome, page, pageSize));
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
        alunoGateway.delete(alunoForDelete.getAlunoId());
    }

    public List<AlunoFindDTO> findByNome(String nome) {
        return alunoAssembler.toAlunoFindDTO(alunoGateway.findBynome(nome));
    }
}
