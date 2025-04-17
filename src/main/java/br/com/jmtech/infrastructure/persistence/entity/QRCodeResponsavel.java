package br.com.jmtech.infrastructure.persistence.entity;

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
    @Column(name = "id_quecode")
    private Long idQRCode;

    @ManyToOne
    @JoinColumn(name = "id_responsavel")
    private ResponsavelAluno responsavel;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @Column(name = "qrcode", length = 8000)
    private String codigoQR;

    @Column(name = "data_geracao")
    private LocalDateTime dataGeracao;

}
