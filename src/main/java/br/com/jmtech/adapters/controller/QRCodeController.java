package br.com.jmtech.adapters.controller;

import br.com.jmtech.application.dto.DetailDTO;
import br.com.jmtech.application.dto.QRCode.QRCodeDTO;
import br.com.jmtech.application.dto.aluno.AlunoDTO;

import br.com.jmtech.application.dto.aluno.AlunoResponsavelDTO;
import br.com.jmtech.domain.usecase.AlunoUseCase;
import br.com.jmtech.domain.usecase.QRCodeUseCase;
import br.com.jmtech.adapters.exception.DataBaseCreateException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping(value = "/securitychildren")
@Tag(name = "QRCode", description = "Operações relacionadas à geração e envio de QRCode")
public class QRCodeController {

    private final AlunoUseCase alunoUseCase;
    private final QRCodeUseCase qrCodeUseCase;

    @Operation(summary = "Lê QR Code do responsável e retorna o aluno vinculado")
    @PostMapping("/api/ler")
    public ResponseEntity<AlunoResponsavelDTO> getQRCode(@RequestBody QRCodeDTO dto) {
        return ResponseEntity.ok(alunoUseCase.findAlunoByQRCode(dto.getQrCode()));
    }

    @Operation(summary = "Gera QR Code para o aluno e envia aos responsáveis")
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
