package br.com.jmtech.application.mapper;

import br.com.jmtech.application.dto.responsavel.ResponsavelCreateDTO;
import br.com.jmtech.infrastructure.domains.ResponsavelAluno;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class ResponsavelAlunoMapper {

    public static final ResponsavelAlunoMapper INSTANCE = Mappers.getMapper(ResponsavelAlunoMapper.class);

    public abstract ResponsavelAluno toResponsavel(ResponsavelCreateDTO responsavelCreateDTO, @MappingTarget ResponsavelAluno newResponsavel, @Context ResponsavelContext responsavelContext);




    public static class ResponsavelContext {

        private final Long idResponsavel;

        public ResponsavelContext(Long idResponsavel) {
            this.idResponsavel = idResponsavel;
        }
    }
}
