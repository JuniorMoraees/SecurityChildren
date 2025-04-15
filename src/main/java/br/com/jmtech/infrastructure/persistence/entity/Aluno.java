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
    @Column(name = "idAluno")
    private Long idAluno;

    @Column(name = "nome")
    private String nome;

    @Column(name = "qr_code")
    private String qrCode;

    @Column(name = "foto")
    private byte[] foto;
}
