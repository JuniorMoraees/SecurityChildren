package br.com.jmtech.adapters.gateway;

import br.com.jmtech.application.dto.PaginatedAnswerDTO;
import br.com.jmtech.infrastructure.persistence.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioGateway {

    Usuario createUsuario(Usuario newUsuario);

    PaginatedAnswerDTO<Usuario> findAll(Integer page, Integer pageSize);

    Optional<Usuario> findUsuarioByLoginAndIdIsNot(String username, Long id);

    Optional<Usuario> findUserByLogin(String username);

    Usuario findByIdOrElseThrow(Long idUsuario);

    Usuario updateResponsavel(Usuario usuarioToUpdate);

    void delete(Long id);
}
