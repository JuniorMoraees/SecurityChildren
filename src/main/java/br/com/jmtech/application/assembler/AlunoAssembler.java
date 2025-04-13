package br.com.jmtech.application.assembler;


import br.com.jmtech.application.dto.aluno.AlunoCreateDTO;
import br.com.jmtech.application.dto.aluno.AlunoDTO;
import br.com.jmtech.application.mapper.AlunoMapper;
import br.com.jmtech.infrastructure.domains.Aluno;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AlunoAssembler {

    public Aluno toAluno (AlunoCreateDTO alunoCreateDTO) {
        return AlunoMapper.INSTANCE.toAluno(alunoCreateDTO, Aluno.builder().build(), new AlunoMapper.AlunoContext(null));
    }

    //metodo incompleto
   /* public Object toAluno (AlunoUpdateDTO alunoUpdateDTO) {
        return null; //mexer depois nessa parte do código
    }*/

    public AlunoDTO toAlunoDTO (Aluno aluno) {
        return AlunoMapper.INSTANCE.toAlunoDTO(aluno);
    }
}
