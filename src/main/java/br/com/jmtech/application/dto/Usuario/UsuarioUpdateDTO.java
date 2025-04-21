package br.com.jmtech.application.dto.Usuario;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UsuarioUpdateDTO {

    private String nome;
    private String username;
    private String senha;
}
