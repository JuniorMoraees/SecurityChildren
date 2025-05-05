package br.com.jmtech.application.assembler;


import br.com.jmtech.application.dto.PaginatedAnswerDTO;
import br.com.jmtech.application.dto.aluno.*;
import br.com.jmtech.application.mapper.AlunoMapper;
import br.com.jmtech.infrastructure.persistence.entity.Aluno;
import br.com.jmtech.infrastructure.persistence.entity.Responsavel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AlunoAssembler {

    public Aluno toAluno (AlunoCreateDTO alunoCreateDTO) {
        return AlunoMapper.INSTANCE.toAluno(alunoCreateDTO, Aluno.builder().build(), new AlunoMapper.AlunoContext(null));
    }

    public Aluno toAluno(AlunoUpdateDTO alunoUpdateDTO, Aluno existAluno, Long idAluno) {
        return AlunoMapper.INSTANCE.toAluno(alunoUpdateDTO, existAluno, new AlunoMapper.AlunoContext(idAluno));
    }

    //metodo incompleto
   /* public Object toAluno (AlunoUpdateDTO alunoUpdateDTO) {
        return null; //mexer depois nessa parte do código
    }*/

    public List<AlunoFindDTO> toAlunoFindDTO (List<Aluno> aluno) {
        return AlunoMapper.INSTANCE.toAlunoFindDTO(aluno);
    }

    public PaginatedAnswerDTO<AlunoDTO> toAlunoDTO (PaginatedAnswerDTO<Aluno> alunos) {
        return PaginatedAnswerDTO.<AlunoDTO>builder()
                .pageMetaData(alunos.getPageMetaData())
                .answerContent(AlunoMapper.INSTANCE.toAlunoDTO(alunos.getAnswerContent()))
                .build();
    }

    public AlunoSearchDTO toAlunoSearchDTO(Aluno aluno) {
        return AlunoMapper.INSTANCE.toAlunoSearchDTO(aluno);
    }

    public AlunoResponsavelDTO toAlunoResponsavelDTO(Aluno aluno, Responsavel responsavel) {
        if (aluno == null || responsavel == null) {
            throw new IllegalArgumentException("Aluno e Responsável não podem ser nulos");
        }
        return new AlunoResponsavelDTO(aluno, responsavel);
    }
}
