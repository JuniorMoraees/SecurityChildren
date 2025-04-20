package br.com.jmtech.application.dto.aluno;


import br.com.jmtech.infrastructure.persistence.entity.Aluno;
import br.com.jmtech.infrastructure.persistence.entity.Responsavel;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AlunoResponsavelDTO {

    private String nomeAluno;
    private byte[] fotoAluno;

    private String nomeResponsavel;
    private String cpfResponsavel;
    private byte[] fotoResponsavel;

    public AlunoResponsavelDTO(Aluno aluno, Responsavel responsavel) {
        this.nomeAluno = aluno.getNome();
        this.fotoAluno = aluno.getFoto();

        this.nomeResponsavel = responsavel.getNome();
        this.cpfResponsavel = responsavel.getCpf();
        this.fotoResponsavel = responsavel.getFoto();
    }
}
