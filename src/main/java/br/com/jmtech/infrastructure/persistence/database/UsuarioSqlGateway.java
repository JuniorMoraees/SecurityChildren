package br.com.jmtech.infrastructure.persistence.database;

import br.com.jmtech.adapters.gateway.UsuarioGateway;
import br.com.jmtech.adapters.repository.UsuarioRepository;
import br.com.jmtech.infrastructure.persistence.entity.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UsuarioSqlGateway implements UsuarioGateway {

    @PersistenceContext
    private final EntityManager manager;

    private final UsuarioRepository usuarioRepository;

    @Override
    public Usuario createUsuario(Usuario newUsuario) {
        return usuarioRepository.save(newUsuario);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public List<Usuario> findByName(String nome) {
        return usuarioRepository.findUsuariosByNome(nome);
    }

    @Override
    public Optional<Usuario> findUsuarioByLoginAndIdIsNot(String username, Long id) {
        return usuarioRepository.findUsuarioByUsernameAndIdIsNot(username, id);
    }

    @Override
    public Optional<Usuario> findUserByLogin(String username) {
        return usuarioRepository.findUserByUsername(username);
    }

    @Override
    public Usuario findByIdOrElseThrow(Long idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado na base de dados"));
    }

    @Override
    public Usuario updateResponsavel(Usuario usuarioToUpdate) {
        manager.clear();
        return usuarioRepository.save(usuarioToUpdate);
    }

    @Override
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }
}
