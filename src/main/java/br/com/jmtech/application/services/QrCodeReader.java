package br.com.jmtech.application.services;

import br.com.jmtech.infrastructure.persistence.entity.Aluno;
import br.com.jmtech.infrastructure.persistence.entity.QRCode;
import br.com.jmtech.adapters.exception.ExpiredQRCodeException;
import br.com.jmtech.adapters.exception.NotFoundException;
import br.com.jmtech.adapters.repository.QRCodeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class QrCodeReader {

    private final QRCodeRepository repository;

    public Aluno leitorQrCode(String qrCode) {
        QRCode codigo = repository.findByCodigoQR(qrCode)
                .orElseThrow(() -> new NotFoundException("QR Code do responsável inválido ou não encontrado"));

        if (!isQRCodeValido(codigo)) {
            throw new ExpiredQRCodeException("QRCode expirado.");
        }

        return codigo.getAluno();
    }

    public boolean isQRCodeValido(QRCode qrCode) {
        if (qrCode.getDataGeracao() == null) {
            return false;
        }

        LocalDateTime agora = LocalDateTime.now();
        Duration duracao = Duration.between(qrCode.getDataGeracao(), agora);

        return duracao.toHours() <= 24;
    }
}
