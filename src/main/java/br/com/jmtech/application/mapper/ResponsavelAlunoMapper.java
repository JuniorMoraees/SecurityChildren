package br.com.jmtech.application.mapper;

import br.com.jmtech.application.dto.responsavel.ResponsavelAlunoDTO;
import br.com.jmtech.application.dto.responsavel.ResponsavelAlunoSearchDTO;
import br.com.jmtech.application.dto.responsavel.ResponsavelAlunoUpdateDTO;
import br.com.jmtech.application.dto.responsavel.ResponsavelAlunoCreateDTO;
import br.com.jmtech.infrastructure.domains.ResponsavelAluno;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ResponsavelAlunoMapper {

    public static final ResponsavelAlunoMapper INSTANCE = Mappers.getMapper(ResponsavelAlunoMapper.class);

    public abstract ResponsavelAluno toResponsavel(ResponsavelAlunoCreateDTO responsavelAlunoCreateDTO, @MappingTarget ResponsavelAluno newResponsavel, @Context ResponsavelContext responsavelContext);

    public abstract ResponsavelAluno toResponsavel(ResponsavelAlunoUpdateDTO responsavelAlunoUpdateDTO, @MappingTarget ResponsavelAluno existResponsavel, @Context ResponsavelContext responsavelContext);

    public abstract List<ResponsavelAlunoDTO> toResponsavelDTO(List<ResponsavelAluno> responsavel);

    public abstract ResponsavelAlunoSearchDTO toResponsavelSearchDTO(ResponsavelAluno responsavel);


    public static class ResponsavelContext {

        private final Long idResponsavel;

        public ResponsavelContext(Long idResponsavel) {
            this.idResponsavel = idResponsavel;
        }
    }
}
