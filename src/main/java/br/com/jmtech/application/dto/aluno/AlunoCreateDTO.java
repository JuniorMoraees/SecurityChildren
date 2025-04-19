package br.com.jmtech.application.dto.aluno;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AlunoCreateDTO {

    private String nome;
    private MultipartFile foto;
}
