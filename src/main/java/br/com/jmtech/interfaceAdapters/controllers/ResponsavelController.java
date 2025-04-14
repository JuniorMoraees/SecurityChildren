package br.com.jmtech.interfaceAdapters.controllers;

import br.com.jmtech.application.dto.DetailDTO;
import br.com.jmtech.application.dto.responsavel.ResponsavelAlunoDTO;
import br.com.jmtech.application.dto.responsavel.ResponsavelAlunoSearchDTO;
import br.com.jmtech.application.dto.responsavel.ResponsavelAlunoUpdateDTO;
import br.com.jmtech.application.dto.responsavel.ResponsavelAlunoCreateDTO;
import br.com.jmtech.application.usecase.ResponsavelUseCase;
import br.com.jmtech.interfaceAdapters.exception.DataBaseCreateException;
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
public class ResponsavelController {

    private static final String CREATED = "Criado com sucesso";
    private static final String UPDATED = "Atualizado com sucesso";
    private static final String DELETED = "Removido com sucesso";
    private static final String SUCCESS_MESSAGE = "Sucesso";

    private final ResponsavelUseCase responsavelUseCase;

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

    @GetMapping("/api/responsaveis")
    public ResponseEntity<List<ResponsavelAlunoDTO>> findAll() {
        return ResponseEntity.ok(responsavelUseCase.findAll());
    }

    @GetMapping("/api/responsaveis/{idResponsavel}")
    public ResponseEntity<ResponsavelAlunoSearchDTO> findById(@PathVariable Long idResponsavel) {
        return  ResponseEntity.ok(responsavelUseCase.findById(idResponsavel));
    }

    @PutMapping("/api/responsaveis/{idResponsavel}")
    public ResponseEntity<DetailDTO> update(@PathVariable long idResponsavel, @Valid @RequestBody ResponsavelAlunoUpdateDTO responsavelAlunoUpdate) throws DataBaseCreateException {
        responsavelUseCase.update(responsavelAlunoUpdate, idResponsavel);
        return ResponseEntity.ok().body(DetailDTO.builder()
                .status(HttpStatus.OK.value())
                .detail(UPDATED)
                .title(SUCCESS_MESSAGE)
                .build());
    }

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
