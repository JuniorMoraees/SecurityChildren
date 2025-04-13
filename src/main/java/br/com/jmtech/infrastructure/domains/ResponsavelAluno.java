package br.com.jmtech.infrastructure.domains;

import lombok.*;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "RESPONSAVEL")
public class ResponsavelAluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idResponsavel")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private String cpf;

    @ElementCollection
    @CollectionTable(name = "responsavel_aluno_ids", joinColumns = @JoinColumn(name = "idResponsavel"))
    @Column(name = "idAluno")
    private List<Integer> alunosIds;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "idResponsavel")
    private List<Telefone> telefones;

    @Column
    private byte[] foto;
}
