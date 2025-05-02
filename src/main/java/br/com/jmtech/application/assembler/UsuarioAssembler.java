package br.com.jmtech.application.assembler;

import br.com.jmtech.application.dto.PaginatedAnswerDTO;
import br.com.jmtech.application.dto.Usuario.UsuarioCreateDTO;
import br.com.jmtech.application.dto.Usuario.UsuarioDTO;
import br.com.jmtech.application.dto.Usuario.UsuarioSearchDTO;
import br.com.jmtech.application.dto.Usuario.UsuarioUpdateDTO;
import br.com.jmtech.application.mapper.UsuarioMapper;
import br.com.jmtech.infrastructure.persistence.entity.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UsuarioAssembler {


    public Usuario toUsuario(UsuarioCreateDTO usuarioCreateDTO) {
        return UsuarioMapper.INSTANCE.toUsuario(usuarioCreateDTO, Usuario.builder().build(), new UsuarioMapper.UsuarioContext(null));
    }

    public Usuario toUsuario(UsuarioUpdateDTO usuarioUpdateDTO, Usuario existUsuario, Long idUsuario) {
        return UsuarioMapper.INSTANCE.toUsuario(usuarioUpdateDTO, existUsuario, new UsuarioMapper.UsuarioContext(idUsuario));
    }

    public PaginatedAnswerDTO<UsuarioDTO> toUsuarioDTO(PaginatedAnswerDTO<Usuario> usuarios) {
        return PaginatedAnswerDTO.<UsuarioDTO>builder()
                .pageMetaData(usuarios.getPageMetaData())
                .answerContent(UsuarioMapper.INSTANCE.toUsuarioDTO(usuarios.getAnswerContent()))
                .build();
    }

    public UsuarioSearchDTO toUsuarioSearchDTO(Usuario usuario) {
        return UsuarioMapper.INSTANCE.toUsuarioSearchDTO(usuario);
    }
}
