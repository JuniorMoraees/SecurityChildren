package br.com.jmtech.application.dto.responsavel;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponsavelAlunoUpdateDTO {
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
