package br.com.jmtech.application.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TipoTelefoneMapper {

//    default TipoTelefone fromDTO(ResponsavelAlunoCreateDTO.TipoTelefoneDTO dto) {
//        if (dto == null || dto.getDescricao() == null) return null;
//
//        return Arrays.stream(TipoTelefone.values())
//                .filter(t -> t.getDescricao().equalsIgnoreCase(dto.getDescricao()))
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("TipoTelefone inválido: " + dto.getDescricao()));
//    }
}
