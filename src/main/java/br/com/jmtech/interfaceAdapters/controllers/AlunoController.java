package br.com.jmtech.interfaceAdapters.controllers;

import br.com.jmtech.application.dto.DetailDTO;
import br.com.jmtech.application.dto.aluno.AlunoCreateDTO;
import br.com.jmtech.application.dto.aluno.AlunoDTO;
import br.com.jmtech.application.dto.aluno.AlunoSearchDTO;
import br.com.jmtech.application.dto.aluno.AlunoUpdateDTO;
import br.com.jmtech.application.usecase.AlunoUseCase;
import br.com.jmtech.infrastructure.domains.Aluno;
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
public class AlunoController {

    private static final String CREATED = "Criado com sucesso";
    private static final String UPDATED = "Atualizado com sucesso";
    private static final String DELETED = "Removido com sucesso";
    private static final String SUCCESS_MESSAGE = "Sucesso";

    private final AlunoUseCase alunoUseCase;

    @PostMapping("/api/alunos")
    public ResponseEntity<DetailDTO> create(@Valid @RequestBody AlunoCreateDTO aluno) {
        Long id = alunoUseCase.create(aluno);
        return ResponseEntity.created(URI.create(String.format("/securitychildren/%d", id)))
                .body(DetailDTO.builder()
                        .status(HttpStatus.CREATED.value())
                        .detail(CREATED)
                        .title(SUCCESS_MESSAGE)
                        .build());
    }

    @GetMapping("/api/alunos")
    public ResponseEntity<List<AlunoDTO>> findAll() {
        return ResponseEntity.ok(alunoUseCase.findAll());
    }

    @GetMapping("/api/alunos/{idAluno}")
    public ResponseEntity<AlunoSearchDTO> findById(@PathVariable Long idAluno) {
        return  ResponseEntity.ok(alunoUseCase.findById(idAluno));
    }

    @PutMapping
    public ResponseEntity<DetailDTO> update(@PathVariable Long idAluno, @Valid @RequestBody AlunoUpdateDTO alunoUpdate) {
        alunoUseCase.update(alunoUpdate, idAluno);
        return ResponseEntity.ok()
                .body(DetailDTO.builder()
                        .status(HttpStatus.OK.value())
                        .detail(DELETED)
                        .title(SUCCESS_MESSAGE)
                        .build());
    }

}
