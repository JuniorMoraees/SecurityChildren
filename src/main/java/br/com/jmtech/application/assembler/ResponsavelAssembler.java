package br.com.jmtech.application.assembler;

import br.com.jmtech.application.dto.aluno.AlunoDTO;
import br.com.jmtech.application.dto.responsavel.ResponsavelAlunoDTO;
import br.com.jmtech.application.dto.responsavel.ResponsavelCreateDTO;
import br.com.jmtech.application.mapper.AlunoMapper;
import br.com.jmtech.application.mapper.ResponsavelAlunoMapper;
import br.com.jmtech.infrastructure.domains.Aluno;
import br.com.jmtech.infrastructure.domains.ResponsavelAluno;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ResponsavelAssembler {

    public ResponsavelAluno toResponsavel(ResponsavelCreateDTO responsavelCreateDTO) {
        return ResponsavelAlunoMapper.INSTANCE.toResponsavel(responsavelCreateDTO, ResponsavelAluno.builder().build(), new ResponsavelAlunoMapper.ResponsavelContext(null));
    }

    public List<ResponsavelAlunoDTO> toResponsavelDTO (List<ResponsavelAluno> responsaveis) {
        return ResponsavelAlunoMapper.INSTANCE.toResponsavelDTO(responsaveis);
    }
}
