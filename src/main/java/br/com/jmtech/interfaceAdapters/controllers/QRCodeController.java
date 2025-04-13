package br.com.jmtech.interfaceAdapters.controllers;

import br.com.jmtech.application.dto.aluno.AlunoDTO;
import br.com.jmtech.application.usecase.AlunoUseCase;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/securitychildren")
public class QRCodeController {

    private final AlunoUseCase alunoUseCase;

    @GetMapping("/api/code/{qrCode}")
    public ResponseEntity<AlunoDTO> getQRCode(@PathVariable String qrCode) {
        return ResponseEntity.ok(alunoUseCase.findAlunoByQRCode(qrCode));
    }


}
