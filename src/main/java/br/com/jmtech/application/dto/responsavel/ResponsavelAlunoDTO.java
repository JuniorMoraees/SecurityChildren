package br.com.jmtech.application.dto.responsavel;

import br.com.jmtech.domain.enums.TipoTelefone;
import br.com.jmtech.infrastructure.persistence.entity.Telefone;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResponsavelAlunoDTO {


    private String nome;
    private String cpf;
    private List<Integer> alunosIds;
    private List<Telefone> telefones;
    private byte[] foto;


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class Telefone {

        private TipoTelefone tipoTelefone;
        private Integer numero;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class TipoTelefone {
        private String descricao;
    }
}
