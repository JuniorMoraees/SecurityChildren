package br.com.jmtech.application.usecase;


import br.com.jmtech.application.assembler.AlunoAssembler;
import br.com.jmtech.application.dto.aluno.AlunoDTO;
import br.com.jmtech.infrastructure.domains.Aluno;
import br.com.jmtech.interfaceAdapters.gateway.AlunoGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@AllArgsConstructor
public class AlunoUseCase {

    private final AlunoGateway alunoGateway;
    private final AlunoAssembler alunoAssembler;

    @PersistenceContext
    private final EntityManager manager;

    public AlunoDTO findAlunoByQRCode(String qrCode) {
        Aluno aluno = alunoGateway.findByQrCodeOrElseThrow(qrCode);
        return alunoAssembler.toAlunoDTO(aluno);
    }
}
