package br.com.jmtech.interfaceAdapters.controllers;

import br.com.jmtech.application.dto.DetailDTO;
import br.com.jmtech.application.dto.aluno.AlunoDTO;

import br.com.jmtech.application.usecase.AlunoUseCase;
import br.com.jmtech.application.usecase.QRCodeUseCase;
import br.com.jmtech.interfaceAdapters.exception.DataBaseCreateException;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping(value = "/securitychildren")
public class QRCodeController {

    private final AlunoUseCase alunoUseCase;
    private final QRCodeUseCase qrCodeUseCase;

    @GetMapping("/api/ler/{qrCode}")
    public ResponseEntity<AlunoDTO> getQRCode(@PathVariable String qrCode) {
        return ResponseEntity.ok(alunoUseCase.findAlunoByQRCode(qrCode));
    }

    @PostMapping("/api/gerar/{idAluno}")
    public ResponseEntity<DetailDTO> gerarQrCode(@PathVariable Long idAluno) {
        try {
            qrCodeUseCase.gerarQrCode(idAluno);
            return ResponseEntity.ok(DetailDTO.builder()
                    .status(HttpStatus.OK.value())
                    .detail("QR Code gerado e enviado com sucesso!")
                    .title("Operação bem-sucedida")
                    .build());
        } catch (DataBaseCreateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(DetailDTO.builder()
                            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .detail("Erro ao gerar QR Code: " + e.getMessage())
                            .title("Erro interno")
                            .build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(DetailDTO.builder()
                            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .detail("Erro inesperado: " + e.getMessage())
                            .title("Erro desconhecido")
                            .build());
        }
    }
}
