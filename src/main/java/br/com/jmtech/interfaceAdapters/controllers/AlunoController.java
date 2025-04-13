package br.com.jmtech.interfaceAdapters.controllers;

import br.com.jmtech.application.dto.DetailDTO;
import br.com.jmtech.application.dto.aluno.AlunoCreateDTO;
import br.com.jmtech.application.usecase.AlunoUseCase;
import br.com.jmtech.infrastructure.domains.Aluno;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/securitychildren")
public class AlunoController {

    private static final String CREATED = "Criado com sucesso";
    private static final String UPDATED = "Atualizado com sucesso";
    private static final String DELETED = "Removido com sucesso";
    private static final String SUCCESS_MESSAGE = "Sucesso";

    private final AlunoUseCase alunoUseCase;

    @PostMapping
    public ResponseEntity<DetailDTO> create(@Valid @RequestBody AlunoCreateDTO aluno) {
        Long id = alunoUseCase.create(aluno);
        return ResponseEntity.created(URI.create(String.format("/securitychildren/%d", id)))
                .body(DetailDTO.builder()
                        .status(HttpStatus.CREATED.value())
                        .detail(CREATED)
                        .title(SUCCESS_MESSAGE)
                        .build());
    }
}
