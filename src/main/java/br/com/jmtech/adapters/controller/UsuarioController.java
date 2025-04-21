package br.com.jmtech.adapters.controller;

import br.com.jmtech.adapters.exception.DataBaseCreateException;
import br.com.jmtech.application.dto.DetailDTO;
import br.com.jmtech.application.dto.Usuario.UsuarioCreateDTO;
import br.com.jmtech.application.dto.Usuario.UsuarioDTO;
import br.com.jmtech.application.dto.Usuario.UsuarioSearchDTO;
import br.com.jmtech.application.dto.Usuario.UsuarioUpdateDTO;
import br.com.jmtech.domain.usecase.UsuarioUseCase;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private static final String CREATED = "Criado com sucesso";
    private static final String UPDATED = "Atualizado com sucesso";
    private static final String DELETED = "Removido com sucesso";
    private static final String SUCCESS_MESSAGE = "Sucesso";

    private final UsuarioUseCase usuarioUseCase;


    @ApiOperation(value = "Cria um novo Usuario", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PostMapping
    public ResponseEntity<DetailDTO> create(@Valid @RequestBody UsuarioCreateDTO usuario) throws DataBaseCreateException {
        Long id = usuarioUseCase.create(usuario);
        return ResponseEntity.created(URI.create(String.format("/usuario/%d", id)))
                .body(DetailDTO.builder()
                        .status(HttpStatus.CREATED.value())
                        .detail(CREATED)
                        .title(SUCCESS_MESSAGE)
                        .build());
    }

    @ApiOperation(value = "Lista todos os usuarios")
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        return ResponseEntity.ok(usuarioUseCase.findAll());
    }

    @ApiOperation(value = "Busca usuario por ID")
    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioSearchDTO> findById(@PathVariable Long idUsuario) {
        return  ResponseEntity.ok(usuarioUseCase.findById(idUsuario));
    }

    @ApiOperation(value = "Atualiza os dados de um usuario")
    @PutMapping("/{idUsuario}")
    public ResponseEntity<DetailDTO> update(@PathVariable Long idUsuario, @Valid @RequestBody UsuarioUpdateDTO usuarioUpdate) throws DataBaseCreateException {
        usuarioUseCase.update(usuarioUpdate, idUsuario);
        return ResponseEntity.ok()
                .body(DetailDTO.builder()
                        .status(HttpStatus.OK.value())
                        .detail(UPDATED)
                        .title(SUCCESS_MESSAGE)
                        .build());
    }

    @ApiOperation(value = "Remove um usuario")
    @DeleteMapping("/{idAluno}")
    public ResponseEntity<DetailDTO> delete(@PathVariable Long idUsuario) {
        usuarioUseCase.delete(idUsuario);
        return ResponseEntity.ok()
                .body(DetailDTO.builder()
                        .status(HttpStatus.OK.value())
                        .detail(DELETED)
                        .title(SUCCESS_MESSAGE)
                        .build());
    }
}
