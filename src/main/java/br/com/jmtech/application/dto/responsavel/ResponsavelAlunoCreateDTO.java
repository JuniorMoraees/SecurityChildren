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
    private List<Long> alunosIds;
    private List<TelefoneCreateDTO> telefones;
//    private MultipartFile foto;


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class TelefoneCreateDTO {

//        private TipoTelefone tipoTelefone;
        private Long numero;
    }

}
