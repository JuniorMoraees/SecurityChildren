package br.com.jmtech.application.services;

import br.com.jmtech.adapters.exception.NotFoundException;
import br.com.jmtech.infrastructure.persistence.entity.*;
import br.com.jmtech.adapters.exception.DataBaseCreateException;
import br.com.jmtech.adapters.repository.*;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import lombok.AllArgsConstructor;
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
import java.util.UUID;

@Service
@AllArgsConstructor
public class QRCodeGenerator {

    private final QRCodeResponsavelRepository qrcodeResponsavelRepository;
    private final ResponsavelAlunoRepository responsavelAlunoRepository;
    private final AlunoRepository alunoRepository;
    private final RegistroEntradaRepository registroEntradaRepository;
    private final QRCodeAlunoRepository qrcodeAlunoRepository;

    public void gerarQrCode(Long idAluno) throws DataBaseCreateException {
        try {
            Aluno aluno = alunoRepository.findById(idAluno)
                    .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

            List<ResponsavelAluno> responsaveis = responsavelAlunoRepository.findByAluno(aluno);
            if (responsaveis.isEmpty()) {
                throw new NotFoundException("Nenhum responsável vinculado ao aluno: " + aluno.getNome());
            }

            String qrCodeAlunoBase64 = gerarImagemQrBase64("QR Code do aluno: " + aluno.getNome());

            QRCodeAluno qrCodeAluno = new QRCodeAluno();
            qrCodeAluno.setAluno(aluno);
            qrCodeAluno.setCodigoQR(qrCodeAlunoBase64);
            qrCodeAluno.setDataGeracao(LocalDateTime.now());
            qrcodeAlunoRepository.save(qrCodeAluno);

            for (ResponsavelAluno responsavelAluno : responsaveis) {
                Responsavel responsavel = responsavelAluno.getResponsavel();
                String qrResponsavelTexto = "Responsável: " + responsavel.getNome() + " - Aluno: " + aluno.getNome();
                String qrCodeResponsavelBase64 = gerarImagemQrBase64(qrResponsavelTexto);

                QRCodeResponsavel qrResponsavel = QRCodeResponsavel.builder()
                        .aluno(aluno)
                        .responsavel(responsavel)
                        .codigoQR(qrCodeResponsavelBase64)
                        .dataGeracao(LocalDateTime.now())
                        .build();

                qrcodeResponsavelRepository.save(qrResponsavel);

                String numeroString = responsavel.getTelefones().get(0).getNumero().toString();
                enviarQrCodeViaWhatsApp(numeroString, aluno.getNome(), qrCodeResponsavelBase64);

                RegistroEntrada registro = new RegistroEntrada();
                registro.setAluno(aluno);
                registro.setCodigoQR(qrCodeResponsavelBase64);
                registro.setResponsavel(responsavel);
                registro.setDataEntrada(LocalDate.now());
                registroEntradaRepository.save(registro);
            }

        } catch (Exception e) {
            throw new DataBaseCreateException("Erro ao gerar ou enviar o QR Code: " + e.getMessage());
        }
    }

    private String gerarImagemQrBase64(String texto) throws WriterException, IOException {
        String conteudoComRandom = texto + "_" + UUID.randomUUID();

        BitMatrix matrix = new MultiFormatWriter().encode(conteudoComRandom, BarcodeFormat.QR_CODE, 200, 200);
        BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < 200; i++) {
            for (int j = 0; j < 200; j++) {
                image.setRGB(i, j, matrix.get(i, j) ? 0x000000 : 0xFFFFFF);
            }
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        byte[] bytes = baos.toByteArray();

        return Base64.getEncoder().encodeToString(bytes);
    }

    private void enviarQrCodeViaWhatsApp(String numero, String nomeAluno, String base64QrCode) {
        String telefoneComDDD = "55" + numero.replaceAll("[^\\d]", "");

        if (base64QrCode.startsWith("data:image")) {
            base64QrCode = base64QrCode.substring(base64QrCode.indexOf(",") + 1);
        }

        String body = String.format(
                "{ \"phone\": \"%s\", \"image\": \"data:image/png;base64,%s\", \"caption\": \"QR Code do aluno %s\", \"viewOnce\": false }",
                telefoneComDDD, base64QrCode, nomeAluno
        );

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.z-api.io/instances/3DFED4F67562C021E3DD4E20A388CB1E/token/47DA74F6E05FCF5E4C59F702/send-image"))
                .header("Content-Type", "application/json")
                .header("Client-Token", "F37554d789a484b2caea5f05e418c6b8aS")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Resposta da API: " + response.body());

            if (response.statusCode() != 200) {
                System.err.println("Erro ao enviar imagem: " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao enviar QR Code via WhatsApp", e);
        }
    }


}

