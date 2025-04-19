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
@Table(name = "tipo_telefone")
public class TipoTelefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_telefone")
    private Long idTipoTelefone;

    @Column(name = "tipo_telefone")
    private String tipoTelefone;
}
