package br.com.jmtech.infrastructure.persistence.database;

import br.com.jmtech.adapters.gateway.UsuarioGateway;
import br.com.jmtech.adapters.repository.UsuarioRepository;
import br.com.jmtech.application.dto.PageDTO;
import br.com.jmtech.application.dto.PaginatedAnswerDTO;
import br.com.jmtech.infrastructure.persistence.entity.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public PaginatedAnswerDTO<Usuario> findAll(String nome, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Usuario> pageResult;

        if (nome != null && !nome.trim().isEmpty()){
            pageResult = usuarioRepository.findByNomeContainingIgnoreCase(nome, pageable);
        }else {
            pageResult = usuarioRepository.findAll(pageable);
        }

        PageDTO pageDTO = new PageDTO();
        pageDTO.setTotalRecords((int) pageResult.getTotalElements());
        pageDTO.setTotalPages(pageResult.getTotalPages());
        pageDTO.setPageNumber(page);
        pageDTO.setPageSize(pageSize);

        return PaginatedAnswerDTO.<Usuario>builder()
                .answerContent(pageResult.getContent())
                .pageMetaData(pageDTO)
                .build();
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
