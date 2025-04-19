package br.com.jmtech.infrastructure.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aluno_id")
    private Long alunoId;

    @Column(name = "nome")
    private String nome;

    @ManyToMany(mappedBy = "alunos")
    private List<Responsavel> responsaveis;

    @Column(name = "qr_code")
    private String qrCode;

    @Column(name = "foto")
    private byte[] foto;
}
