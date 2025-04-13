package br.com.jmtech.application.dto.aluno;

import br.com.jmtech.infrastructure.domains.ResponsavelAluno;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AlunoDTO {

    private String nome;
    private byte[] foto;

    private ResponsavelAlunoDTO responsavelAluno;


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class ResponsavelAlunoDTO {
        private byte[] foto;
    }

}
