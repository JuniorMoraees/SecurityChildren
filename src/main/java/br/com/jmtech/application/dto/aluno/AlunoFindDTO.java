package br.com.jmtech.application.dto.aluno;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AlunoFindDTO {

    private Long alunoId;
    private String nome;
}
