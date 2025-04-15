package br.com.jmtech.application.assembler;


import br.com.jmtech.application.dto.responsavel.ResponsavelAlunoDTO;
import br.com.jmtech.application.dto.responsavel.ResponsavelAlunoSearchDTO;
import br.com.jmtech.application.dto.responsavel.ResponsavelAlunoUpdateDTO;
import br.com.jmtech.application.dto.responsavel.ResponsavelAlunoCreateDTO;

import br.com.jmtech.application.mapper.ResponsavelAlunoMapper;

import br.com.jmtech.infrastructure.persistence.entity.ResponsavelAluno;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ResponsavelAssembler {

    public ResponsavelAluno toResponsavel(ResponsavelAlunoCreateDTO responsavelAlunoCreateDTO) {
        return ResponsavelAlunoMapper.INSTANCE.toResponsavel(responsavelAlunoCreateDTO, ResponsavelAluno.builder().build(), new ResponsavelAlunoMapper.ResponsavelContext(null));
    }

    public ResponsavelAluno toResponsavel(ResponsavelAlunoUpdateDTO responsavelUpdateDTO, ResponsavelAluno existResponsavel, long idResponsavel) {
        return ResponsavelAlunoMapper.INSTANCE.toResponsavel(responsavelUpdateDTO, existResponsavel, new ResponsavelAlunoMapper.ResponsavelContext(idResponsavel));
    }

    public List<ResponsavelAlunoDTO> toResponsavelDTO (List<ResponsavelAluno> responsaveis) {
        return ResponsavelAlunoMapper.INSTANCE.toResponsavelDTO(responsaveis);
    }

    public ResponsavelAlunoSearchDTO toResponsavelSearchDTO(ResponsavelAluno responsavel) {
        return ResponsavelAlunoMapper.INSTANCE.toResponsavelSearchDTO(responsavel);
    }
}
