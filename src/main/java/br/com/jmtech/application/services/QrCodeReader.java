package br.com.jmtech.application.services;

import br.com.jmtech.adapters.repository.QRCodeResponsavelRepository;
import br.com.jmtech.application.dto.aluno.AlunoResponsavelDTO;
import br.com.jmtech.infrastructure.persistence.entity.Aluno;
import br.com.jmtech.infrastructure.persistence.entity.QRCode;
import br.com.jmtech.adapters.exception.ExpiredQRCodeException;
import br.com.jmtech.adapters.exception.NotFoundException;
import br.com.jmtech.adapters.repository.QRCodeRepository;
import br.com.jmtech.infrastructure.persistence.entity.QRCodeResponsavel;
import br.com.jmtech.infrastructure.persistence.entity.Responsavel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class QrCodeReader {

    private final QRCodeResponsavelRepository repository;

    public QRCodeResponsavel leitorQrCode(String qrCode) {
        QRCodeResponsavel codigo = repository.findByCodigoQR(qrCode)
                .orElseThrow(() -> new NotFoundException("QR Code do responsável inválido ou não encontrado"));

        if (!isQRCodeValido(codigo)) {
            throw new ExpiredQRCodeException("QRCode expirado.");
        }

        return codigo;
    }

    public boolean isQRCodeValido(QRCodeResponsavel qrCode) {
        if (qrCode.getDataGeracao() == null) {
            return false;
        }

        LocalDateTime agora = LocalDateTime.now();
        Duration duracao = Duration.between(qrCode.getDataGeracao(), agora);

        return duracao.toHours() <= 1;
    }
}
