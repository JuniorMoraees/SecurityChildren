package br.com.jmtech.infrastructure.domains;

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
@Table(name = "QRCode")
public class QRCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idQRCode;

    @ManyToOne
    private Aluno aluno;

    @ElementCollection
    @CollectionTable(name = "responsavel_qrcode", joinColumns = @JoinColumn(name = "idQRCode"))
    @Column(name = "idResponsavel")
    private List<Integer> responsaveis;

    @Column
    private String codigoQR;

    @Column
    private String dataGeracao;
}
