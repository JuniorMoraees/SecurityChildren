package br.com.jmtech.infrastructure.domains;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "QRCodeAluno")
public class QRCodeAluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Aluno aluno;

    @Column(length = 2000)
    private String codigoQR;

    private LocalDateTime dataGeracao;
}
