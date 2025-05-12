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

      @Column(name = "nome_aluno")
      private String nomeAluno;


      @Column(name = "nome_responsavel")
      private String nomeResponsavel;

      @Column(name = "data_entrada")
      private LocalDate dataEntrada;

      @Column(name = "qrcode", length = 8000, nullable = false)
      private String codigoQR;

}
