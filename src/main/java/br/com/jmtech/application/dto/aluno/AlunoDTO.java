package br.com.jmtech.application.dto.aluno;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AlunoDTO {

    private Long alunoId;
    private String nome;
    private String foto;
    private ResponsavelAlunoDTO responsavelAluno;


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class ResponsavelAlunoDTO {
        private String foto;
    }

}
