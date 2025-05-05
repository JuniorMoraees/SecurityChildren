package br.com.jmtech.adapters.controller;

import br.com.jmtech.application.dto.DetailDTO;
import br.com.jmtech.application.dto.PaginatedAnswerDTO;
import br.com.jmtech.application.dto.responsavel.ResponsavelAlunoDTO;
import br.com.jmtech.application.dto.responsavel.ResponsavelAlunoSearchDTO;
import br.com.jmtech.application.dto.responsavel.ResponsavelAlunoUpdateDTO;
import br.com.jmtech.application.dto.responsavel.ResponsavelAlunoCreateDTO;
import br.com.jmtech.domain.usecase.ResponsavelUseCase;
import br.com.jmtech.adapters.exception.DataBaseCreateException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/securitychildren")
@Tag(name = "Responsáveis", description = "Operações relacionadas aos dados dos responsaveis")
public class ResponsavelController {

    private static final String CREATED = "Criado com sucesso";
    private static final String UPDATED = "Atualizado com sucesso";
    private static final String DELETED = "Removido com sucesso";
    private static final String SUCCESS_MESSAGE = "Sucesso";

    private final ResponsavelUseCase responsavelUseCase;

    @Operation(summary = "Cria um novo responsável")
    @PostMapping("/api/responsaveis")
    public ResponseEntity<DetailDTO> create(@Valid @RequestBody ResponsavelAlunoCreateDTO responsavel) throws DataBaseCreateException {
        Long id = responsavelUseCase.create(responsavel);
        return ResponseEntity.created(URI.create(String.format("/securitychildren/%d", id)))
                .body(DetailDTO.builder()
                        .status(HttpStatus.CREATED.value())
                        .detail(CREATED)
                        .title(SUCCESS_MESSAGE)
                        .build());
    }

    @Operation(summary = "Busca todos os responsáveis")
    @GetMapping("/api/responsaveis")
    public ResponseEntity<PaginatedAnswerDTO<ResponsavelAlunoDTO>> findAll(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return ResponseEntity.ok(responsavelUseCase.findAll(nome, page, pageSize));
    }

    @Operation(summary = "Busca responsável por ID")
    @GetMapping("/api/responsaveis/{idResponsavel}")
    public ResponseEntity<ResponsavelAlunoSearchDTO> findById(@PathVariable Long idResponsavel) {
        return  ResponseEntity.ok(responsavelUseCase.findById(idResponsavel));
    }

    @Operation(summary = "Atualiza um responsável")
    @PutMapping("/api/responsaveis/{idResponsavel}")
    public ResponseEntity<DetailDTO> update(@PathVariable long idResponsavel, @Valid @RequestBody ResponsavelAlunoUpdateDTO responsavelAlunoUpdate) throws DataBaseCreateException {
        responsavelUseCase.update(responsavelAlunoUpdate, idResponsavel);
        return ResponseEntity.ok().body(DetailDTO.builder()
                .status(HttpStatus.OK.value())
                .detail(UPDATED)
                .title(SUCCESS_MESSAGE)
                .build());
    }

    @Operation(summary = "Deleta um responsável")
    @DeleteMapping("/api/responsaveis/{idResponsavel}")
    public ResponseEntity<DetailDTO> delete(@PathVariable long idResponsavel) {
        responsavelUseCase.delete(idResponsavel);
        return ResponseEntity.ok().body(DetailDTO.builder()
                .status(HttpStatus.OK.value())
                .detail(DELETED)
                .title(SUCCESS_MESSAGE)
                .build());
    }
}
