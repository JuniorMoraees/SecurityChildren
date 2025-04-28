package br.com.jmtech.application.dto.aluno;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AlunoSearchDTO {

    private String nome;
    private String foto;
}
