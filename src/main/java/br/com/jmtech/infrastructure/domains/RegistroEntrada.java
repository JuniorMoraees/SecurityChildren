package br.com.jmtech.infrastructure.domains;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "RegistroEntrada")
public class RegistroEntrada {

   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idAluno")
    private Aluno aluno;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idResponsavel")
    private ResponsavelAluno responsavel;

    @Column
    private LocalDate dataEntrada;

    @Column(length = 2000, nullable = false)
    private String codigoQR;

}
