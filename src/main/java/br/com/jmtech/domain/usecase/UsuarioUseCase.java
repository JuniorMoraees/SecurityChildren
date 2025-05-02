package br.com.jmtech.domain.usecase;


import br.com.jmtech.adapters.exception.DataBaseCreateException;
import br.com.jmtech.adapters.gateway.UsuarioGateway;
import br.com.jmtech.application.assembler.UsuarioAssembler;
import br.com.jmtech.application.dto.PaginatedAnswerDTO;
import br.com.jmtech.application.dto.Usuario.UsuarioCreateDTO;
import br.com.jmtech.application.dto.Usuario.UsuarioDTO;
import br.com.jmtech.application.dto.Usuario.UsuarioSearchDTO;
import br.com.jmtech.application.dto.Usuario.UsuarioUpdateDTO;
import br.com.jmtech.infrastructure.persistence.entity.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioUseCase {

    private static final String[] SORT_PARAMETERS = {};

    private final UsuarioGateway usuarioGateway;
    private final UsuarioAssembler usuarioAssembler;
    private final BCryptPasswordEncoder passwordEncoder;

    @PersistenceContext
    private final EntityManager manager;

    public Long create(UsuarioCreateDTO usuarioCreateDTO) throws DataBaseCreateException {
        Usuario newUsuario = usuarioAssembler.toUsuario(usuarioCreateDTO);
        isExistUsuario(newUsuario);
        String senhaCriptografada = passwordEncoder.encode(newUsuario.getSenha());
        newUsuario.setSenha(senhaCriptografada);
        return usuarioGateway.createUsuario(newUsuario).getId();
    }

    public PaginatedAnswerDTO<UsuarioDTO> findAll(Integer page, Integer pageSize) {
        return usuarioAssembler.toUsuarioDTO(usuarioGateway.findAll(page, pageSize));
    }


    public void isExistUsuario(Usuario usuario) throws DataBaseCreateException {
        Optional<Usuario> usuarioDb;
        if (usuario.getUsername() != null) {
            if (usuario.getId() != null ) {
                usuarioDb = usuarioGateway.findUsuarioByLoginAndIdIsNot(usuario.getUsername(), usuario.getId());
            }else {
                usuarioDb = usuarioGateway.findUserByLogin(usuario.getUsername());
            }
            if (usuarioDb.isPresent()) {
                throw new DataBaseCreateException("Usuario de LOGIN: " + usuario.getUsername() + " já cadastrado no sistema");
            }
        }
    }

    public UsuarioSearchDTO findById(Long idUsuario) {
        Usuario usuario = usuarioGateway.findByIdOrElseThrow(idUsuario);
        return usuarioAssembler.toUsuarioSearchDTO(usuario);
    }

    public void update(UsuarioUpdateDTO usuarioUpdateDTO, long idUsuario) throws DataBaseCreateException {
        Usuario usuarioToUpdate = mapToUsuario(usuarioUpdateDTO, idUsuario);
        manager.clear();
        isExistUsuario(usuarioToUpdate);
        usuarioGateway.updateResponsavel(usuarioToUpdate);
    }

    private Usuario mapToUsuario(UsuarioUpdateDTO usuarioUpdate, long idUsuario) {
        Usuario existUsuario = usuarioGateway.findByIdOrElseThrow(idUsuario);
        return usuarioAssembler.toUsuario(usuarioUpdate, existUsuario, idUsuario);
    }

    public void delete(long idUsuario) {
        Usuario usuarioForDelete = usuarioGateway.findByIdOrElseThrow(idUsuario);
        usuarioGateway.delete(usuarioForDelete.getId());
    }
}
