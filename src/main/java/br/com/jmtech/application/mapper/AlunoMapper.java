package br.com.jmtech.application.mapper;


import br.com.jmtech.application.dto.aluno.AlunoCreateDTO;
import br.com.jmtech.application.dto.aluno.AlunoDTO;
import br.com.jmtech.application.dto.aluno.AlunoSearchDTO;
import br.com.jmtech.application.dto.aluno.AlunoUpdateDTO;
import br.com.jmtech.infrastructure.persistence.entity.Aluno;
import br.com.jmtech.infrastructure.persistence.entity.Responsavel;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class AlunoMapper {

    public static final AlunoMapper INSTANCE = Mappers.getMapper(AlunoMapper.class);

    @Mapping(target = "responsavelAluno.foto", source = "responsavel.foto")
    @Mapping(target = "nome", source = "aluno.nome")
    @Mapping(target = "foto", source = "aluno.foto")
    public abstract AlunoDTO toAlunoDTO(Aluno aluno, Responsavel responsavel);

    public abstract List<AlunoDTO> toAlunoDTO(List<Aluno> aluno);

    public abstract Aluno toAluno(AlunoCreateDTO alunoCreateDTO, @MappingTarget Aluno newClient, @Context AlunoContext alunoContext);

    public abstract Aluno toAluno(AlunoUpdateDTO clientUpdateDTO, @MappingTarget Aluno existAluno, @Context AlunoContext alunoContext);

    public abstract AlunoSearchDTO toAlunoSearchDTO(Aluno aluno);


    public static class AlunoContext {

        private final Long idAluno;

        public AlunoContext(Long idAluno) {
            this.idAluno = idAluno;
        }

    }

    byte[] map(MultipartFile file) {
        try {
            return (file != null && !file.isEmpty()) ? file.getBytes() : null;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao converter MultipartFile para byte[]", e);
        }
    }
}
