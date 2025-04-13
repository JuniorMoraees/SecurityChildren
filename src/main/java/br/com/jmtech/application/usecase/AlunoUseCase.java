package br.com.jmtech.application.usecase;


import br.com.jmtech.application.assembler.AlunoAssembler;
import br.com.jmtech.application.dto.aluno.AlunoCreateDTO;
import br.com.jmtech.application.dto.aluno.AlunoDTO;
import br.com.jmtech.application.services.QrCodeReader;
import br.com.jmtech.infrastructure.domains.Aluno;
import br.com.jmtech.interfaceAdapters.gateway.AlunoGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@Service
@AllArgsConstructor
public class AlunoUseCase {

    private final AlunoGateway alunoGateway;
    private final AlunoAssembler alunoAssembler;
    private final QrCodeReader qrCodeReader;

    @PersistenceContext
    private final EntityManager manager;

    public AlunoDTO findAlunoByQRCode(String qrCode) {
        Aluno aluno = qrCodeReader.leitorQrCode(qrCode);
        return alunoAssembler.toAlunoDTO(aluno);
    }

    public Long create(@Valid AlunoCreateDTO alunoDTO) {
        Aluno aluno = alunoAssembler.toAluno(alunoDTO);
//        isExisteAluno(aluno);
        return alunoGateway.createAluno(aluno).getIdAluno();
    }
}
