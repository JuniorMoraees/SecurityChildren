package br.com.jmtech.application.dto.aluno;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AlunoSearchDTO {

    private String nome;
    private byte[] foto;
    private AlunoDTO.ResponsavelAlunoDTO responsavelAluno;


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class ResponsavelAlunoDTO {
        private byte[] foto;
    }
}
