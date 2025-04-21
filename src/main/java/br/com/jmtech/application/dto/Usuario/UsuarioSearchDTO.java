package br.com.jmtech.application.dto.Usuario;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UsuarioSearchDTO {

    private String nome;
    private String username;
}
