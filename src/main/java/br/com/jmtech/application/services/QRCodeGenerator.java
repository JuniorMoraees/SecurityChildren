package br.com.jmtech.application.services;

import br.com.jmtech.infrastructure.domains.Aluno;
import br.com.jmtech.infrastructure.domains.QRCode;
import br.com.jmtech.infrastructure.domains.ResponsavelAluno;
import br.com.jmtech.interfaceAdapters.repositories.AlunoRepository;
import br.com.jmtech.interfaceAdapters.repositories.QRCodeRepository;
import br.com.jmtech.interfaceAdapters.repositories.ResponsavelRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

@Service
public class QRCodeGenerator {

    private final QRCodeRepository codeRepository;
    private final ResponsavelRepository responsavelRepository;
    private final AlunoRepository alunoRepository;

    public QRCodeGenerator(QRCodeRepository codeRepository, ResponsavelRepository responsavelRepository, AlunoRepository alunoRepository) {
        this.codeRepository = codeRepository;
        this.responsavelRepository = responsavelRepository;
        this.alunoRepository = alunoRepository;
    }

    public QRCode gerarQrCode(Aluno aluno) throws Exception {
        String textoQR = "QR Code do aluno: " + aluno.getNome();

        BitMatrix matrix = new MultiFormatWriter().encode(textoQR, BarcodeFormat.QR_CODE, 200, 200);
        BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < 200; i++) {
            for (int j = 0; j < 200; j++) {
                image.setRGB(i, j, matrix.get(i, j) ? 0x000000 : 0xFFFFFF);
            }
        }

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        ImageIO.write(image, "PNG", bytes);
        String qrCodeBase64 = Base64.getEncoder().encodeToString(bytes.toByteArray());

        aluno.setQrCode(qrCodeBase64);
        alunoRepository.save(aluno);

        QRCode qrCode = new QRCode();
        qrCode.setAluno(aluno);
        qrCode.setCodigoQR(qrCodeBase64);
        qrCode.setDataGeracao(LocalDate.now().toString());
        codeRepository.save(qrCode);

        List<ResponsavelAluno> responsaveis = responsavelRepository.findByAlunosIdsContaining(aluno.getIdAluno());
        for (ResponsavelAluno responsavel : responsaveis) {
            responsavel.setCodigoQR(qrCodeBase64);
            responsavelRepository.save(responsavel);
        }

        return qrCode;
    }
}
