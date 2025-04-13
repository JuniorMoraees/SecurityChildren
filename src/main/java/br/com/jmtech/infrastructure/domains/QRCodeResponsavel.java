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
@Table(name = "QRCodeResponsavel")
public class QRCodeResponsavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idQRCode;

    @ManyToOne
    private ResponsavelAluno responsavel;

    @ManyToOne
    private Aluno aluno;

    @Column(length = 2000)
    private String codigoQR;

    private LocalDateTime dataGeracao;

}
