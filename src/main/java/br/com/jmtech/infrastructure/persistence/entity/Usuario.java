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
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "login", nullable = false, unique = true)
    private String username;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "nome")
    private String nome;
}
