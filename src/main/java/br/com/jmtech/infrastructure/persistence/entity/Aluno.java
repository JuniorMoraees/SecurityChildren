package br.com.jmtech.infrastructure.persistence.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "ALUNO")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aluno_id")
    private Long alunoId;

    @Column(name = "nome")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "responsavel_id")
    private ResponsavelAluno responsavel;

    @Column(name = "qr_code")
    private String qrCode;

    @Column(name = "foto")
    private byte[] foto;
}
