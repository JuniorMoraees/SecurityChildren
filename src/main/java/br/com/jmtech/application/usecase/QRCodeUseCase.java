package br.com.jmtech.application.usecase;

import br.com.jmtech.application.services.QRCodeGenerator;
import br.com.jmtech.interfaceAdapters.exception.DataBaseCreateException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QRCodeUseCase {

    private final QRCodeGenerator qrCodeGenerator;

    public void gerarQrCode(Long idAluno) throws DataBaseCreateException {
        qrCodeGenerator.gerarQrCode(idAluno);
    }
}
