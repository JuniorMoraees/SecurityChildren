package br.com.jmtech.application.dto.responsavel;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponsavelAlunoDTO {
    private Long id;
    private String nome;
    private String foto;
}
