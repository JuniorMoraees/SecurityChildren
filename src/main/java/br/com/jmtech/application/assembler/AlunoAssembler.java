package br.com.jmtech.application.assembler;


import br.com.jmtech.application.dto.aluno.AlunoCreateDTO;
import br.com.jmtech.application.dto.aluno.AlunoDTO;
import br.com.jmtech.application.dto.aluno.AlunoSearchDTO;
import br.com.jmtech.application.dto.aluno.AlunoUpdateDTO;
import br.com.jmtech.application.mapper.AlunoMapper;
import br.com.jmtech.infrastructure.persistence.entity.Aluno;
import br.com.jmtech.infrastructure.persistence.entity.ResponsavelAluno;
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

    public AlunoDTO toAlunoDTO (Aluno aluno, ResponsavelAluno responsavel) {
        return AlunoMapper.INSTANCE.toAlunoDTO(aluno, responsavel);
    }

    public List<AlunoDTO> toAlunoDTO (List<Aluno> alunos) {
        return AlunoMapper.INSTANCE.toAlunoDTO(alunos);
    }

    public AlunoSearchDTO toAlunoSearchDTO(Aluno aluno) {
        return AlunoMapper.INSTANCE.toAlunoSearchDTO(aluno);
    }
}
