package br.com.jmtech.application.services;

import br.com.jmtech.infrastructure.domains.*;
import br.com.jmtech.interfaceAdapters.exception.DataBaseCreateException;
import br.com.jmtech.interfaceAdapters.repositories.*;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import lombok.AllArgsConstructor;
import org.apache.catalina.Store;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

@Service
@AllArgsConstructor
public class QRCodeGenerator {

    private final QRCodeResponsavelRepository qrcodeResponsavelRepository;
    private final ResponsavelRepository responsavelRepository;
    private final AlunoRepository alunoRepository;
    private final RegistroEntradaRepository registroEntradaRepository;

    public void gerarQrCode(Long idAluno) throws DataBaseCreateException {
        try {
            Aluno aluno = alunoRepository.findById(idAluno)
                    .orElseThrow(() -> new DataBaseCreateException("Aluno não encontrado"));

            String qrCodeAlunoBase64 = gerarImagemQrBase64("QR Code do aluno: " + aluno.getNome());

            aluno.setQrCode(qrCodeAlunoBase64);
            alunoRepository.save(aluno);

            qrcodeResponsavelRepository.deleteByAluno(aluno);

            List<ResponsavelAluno> responsaveis = responsavelRepository.findByAlunosIdsContaining(aluno.getIdAluno());
            for (ResponsavelAluno responsavel : responsaveis) {
                String qrResponsavelTexto = "Responsável: " + responsavel.getNome() + " - Aluno: " + aluno.getNome();
                String qrCodeResponsavelBase64 = gerarImagemQrBase64(qrResponsavelTexto);

                QRCodeResponsavel qrResponsavel = QRCodeResponsavel.builder()
                        .aluno(aluno)
                        .responsavel(responsavel)
                        .codigoQR(qrCodeResponsavelBase64)
                        .dataGeracao(LocalDateTime.now())
                        .build();

                qrcodeResponsavelRepository.save(qrResponsavel);

                enviarQrCodeViaWhatsApp(responsavel.getCpf(), aluno.getNome(), qrCodeResponsavelBase64);

                RegistroEntrada registro = new RegistroEntrada();
                registro.setAluno(aluno);
                registro.setCodigoQR(qrCodeResponsavelBase64);
                registro.setResponsavel(responsavel);
                registro.setDataEntrada(LocalDate.now());
                registroEntradaRepository.save(registro);
            }

        } catch (Exception e) {
            throw new DataBaseCreateException("Erro ao gerar ou enviar o QR Code");
        }
    }

    private String gerarImagemQrBase64(String texto) throws WriterException, IOException {
        BitMatrix matrix = new MultiFormatWriter().encode(texto, BarcodeFormat.QR_CODE, 200, 200);
        BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < 200; i++) {
            for (int j = 0; j < 200; j++) {
                image.setRGB(i, j, matrix.get(i, j) ? 0x000000 : 0xFFFFFF);
            }
        }

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        ImageIO.write(image, "PNG", bytes);
        return Base64.getEncoder().encodeToString(bytes.toByteArray());
    }

    private void enviarQrCodeViaWhatsApp(String numero, String nomeAluno, String base64QrCode) {
        String telefoneComDDD = "55" + numero.replaceAll("[^\\d]", "");

        String body = String.format(
                "{\"phone\": \"%s\",\"message\": \"QR Code do aluno %s\",\"image\": \"data:image/png;base64,%s\"}",
                telefoneComDDD, nomeAluno, base64QrCode
        );

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.z-api.io/instances/SUA_INSTANCIA/token/SEU_TOKEN/send-image-base64"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        try {
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao enviar QR Code via WhatsApp", e);
        }
    }

}

