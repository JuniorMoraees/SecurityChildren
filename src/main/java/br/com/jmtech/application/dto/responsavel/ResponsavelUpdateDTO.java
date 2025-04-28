package br.com.jmtech.application.dto.responsavel;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponsavelUpdateDTO {

    private String nome;
    private String cpf;
    private List<Long> alunosIds;
    private List<TelefoneCreateDTO> telefones;
    private String foto;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TelefoneCreateDTO {
        private Long numero;
        private Long tipoTelefoneId;
    }
}
