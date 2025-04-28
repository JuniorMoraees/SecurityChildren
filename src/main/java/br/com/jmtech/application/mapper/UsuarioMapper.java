package br.com.jmtech.application.mapper;

import br.com.jmtech.application.dto.Usuario.UsuarioCreateDTO;
import br.com.jmtech.application.dto.Usuario.UsuarioDTO;
import br.com.jmtech.application.dto.Usuario.UsuarioSearchDTO;
import br.com.jmtech.application.dto.Usuario.UsuarioUpdateDTO;
import br.com.jmtech.infrastructure.persistence.entity.Usuario;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UsuarioMapper {

    public static final UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    public abstract Usuario toUsuario(UsuarioCreateDTO usuarioCreateDTO, @MappingTarget Usuario newUsuario, @Context UsuarioMapper.UsuarioContext alunoContext);

    public abstract Usuario toUsuario(UsuarioUpdateDTO usuarioUpdateDTO, @MappingTarget Usuario existUsuario, @Context UsuarioMapper.UsuarioContext alunoContext);

    public abstract List<UsuarioDTO> toUsuarioDTO(List<Usuario> usuarios);

    public abstract UsuarioSearchDTO toUsuarioSearchDTO(Usuario usuario);


    public static class UsuarioContext {

        private final Long idUsuario;

        public UsuarioContext(Long idUsuario) {
            this.idUsuario = idUsuario;
        }

    }
}
