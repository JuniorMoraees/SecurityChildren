package br.com.jmtech.application.mapper;

import br.com.jmtech.application.dto.responsavel.ResponsavelDTO;
import br.com.jmtech.application.dto.responsavel.ResponsavelSearchDTO;
import br.com.jmtech.application.dto.responsavel.ResponsavelUpdateDTO;
import br.com.jmtech.application.dto.responsavel.ResponsavelCreateDTO;
import br.com.jmtech.infrastructure.persistence.entity.Responsavel;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Mapper(componentModel = "spring", uses = {AlunoMapper.class, TipoTelefoneMapper.class})
public abstract class ResponsavelAlunoMapper {

    public static final ResponsavelAlunoMapper INSTANCE = Mappers.getMapper(ResponsavelAlunoMapper.class);

//    public abstract Responsavel toResponsavel(ResponsavelCreateDTO responsavelCreateDTO, @MappingTarget Responsavel newResponsavel, @Context ResponsavelContext responsavelContext);

    public abstract Responsavel toResponsavel(ResponsavelUpdateDTO responsavelUpdateDTO, @MappingTarget Responsavel existResponsavel, @Context ResponsavelContext responsavelContext);

    public abstract List<ResponsavelDTO> toResponsavelDTO(List<Responsavel> responsavel);

    public abstract ResponsavelSearchDTO toResponsavelSearchDTO(Responsavel responsavel);

    public abstract ResponsavelDTO toResponsavelAlunoDTO(Responsavel responsavel);


    public static class ResponsavelContext {

        private final Long idResponsavel;

        public ResponsavelContext(Long idResponsavel) {
            this.idResponsavel = idResponsavel;
        }
    }

    @Named("")
    byte[] map(MultipartFile file) {
        try {
            return (file != null && !file.isEmpty()) ? file.getBytes() : null;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao converter MultipartFile para byte[]", e);
        }
    }
}
