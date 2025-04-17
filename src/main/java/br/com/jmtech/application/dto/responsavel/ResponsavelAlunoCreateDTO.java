package br.com.jmtech.application.dto.responsavel;

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
    private List<Integer> alunosIds;
    private List<TelefoneCreateDTO> telefones;
    private byte[] foto;


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class TelefoneCreateDTO {

        private TipoTelefoneDTO tipoTelefone;
        private Integer numero;
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class TipoTelefoneDTO {
        private String descricao;
    }
}
