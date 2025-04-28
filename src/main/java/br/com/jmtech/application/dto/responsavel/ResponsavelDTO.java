package br.com.jmtech.application.dto.responsavel;

import br.com.jmtech.application.dto.aluno.AlunoDTO;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResponsavelDTO {


    private String nome;
    private String cpf;
    private List<AlunoDTO> alunos;
    private List<Telefone> telefones;
    private String foto;


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class Telefone {

        private TipoTelefone tipoTelefone;
        private Long numero;
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
