package br.com.jmtech.application.dto.aluno;

import br.com.jmtech.application.dto.responsavel.ResponsavelAlunoDTO;
import br.com.jmtech.application.dto.responsavel.ResponsavelDTO;
import br.com.jmtech.infrastructure.persistence.entity.Responsavel;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AlunoDTO {

    private Long alunoId;
    private String nome;
    private String foto;
    private List<ResponsavelAlunoDTO> responsavelAluno;

}
