package br.com.jmtech.application.dto.aluno;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AlunoCreateDTO {

    private String nome;
    private String qrCode;
    private byte[] foto;
}
