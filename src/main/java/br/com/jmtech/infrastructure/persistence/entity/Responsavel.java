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
@Table(name = "responsavel")
public class Responsavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idResponsavel")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private String cpf;

    @ManyToMany
    @JoinTable(
            name = "responsavel_aluno",
            joinColumns = @JoinColumn(name = "idResponsavel"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id")
    )
    private List<Aluno> alunos;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "idResponsavel")
    private List<Telefone> telefones;

    @Column(name = "foto")
    private byte[] foto;

    /*@Column(name = "ativo")
    private Boolean ativo = true;

    @PrePersist
    public void prePersist() {
        if (ativo == null) {
            ativo = true;
        }
    }*/
}
