package br.com.jmtech.adapters.repository;

import br.com.jmtech.application.dto.PaginatedAnswerDTO;
import br.com.jmtech.infrastructure.persistence.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsername(String username);

    Optional<Usuario> findUserByUsername(String username);

    Optional<Usuario> findUsuarioByUsernameAndIdIsNot(String username, Long id);

    Page<Usuario> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
