package br.com.jmtech.infrastructure.domains;

import br.com.jmtech.application.enums.TipoTelefone;
import lombok.*;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "TELEFONE")
public class Telefone {

    @Id
    @Column(name = "idTelefone")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoTelefone")
    private TipoTelefone tipoTelefone;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "idResponsavel")
    private Integer idResponsavel;
}
