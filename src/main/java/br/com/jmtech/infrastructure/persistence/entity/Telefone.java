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
@Table(name = "telefone")
public class Telefone {

    @Id
    @Column(name = "idTelefone")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_tipo_telefone", referencedColumnName = "id_tipo_telefone")
    private TipoTelefone tipoTelefone;

    @Column(name = "numero")
    private Long numero;
}
