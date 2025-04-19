package br.com.jmtech.infrastructure.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "registro_entrada")
public class RegistroEntrada {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column(name = "id_registro")
      private Long id;

      @ManyToOne(optional = false)
      @JoinColumn(name = "aluno_id")
      private Aluno aluno;

      @ManyToOne(optional = false)
      @JoinColumn(name = "idResponsavel")
      private Responsavel responsavel;

      @Column(name = "data_entrada")
      private LocalDate dataEntrada;

      @Column(name = "qrcode", length = 8000, nullable = false)
      private String codigoQR;

}
