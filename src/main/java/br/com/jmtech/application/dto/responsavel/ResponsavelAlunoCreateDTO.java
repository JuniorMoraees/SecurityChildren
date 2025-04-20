package br.com.jmtech.application.dto.responsavel;

import br.com.jmtech.infrastructure.persistence.entity.TipoTelefone;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResponsavelAlunoCreateDTO {

    private String nome;
    private String cpf;
    private List<Long> alunosIds;
    private List<TelefoneCreateDTO> telefones;

    // Se quiser trabalhar com upload futuramente:
    // private MultipartFile foto;

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
