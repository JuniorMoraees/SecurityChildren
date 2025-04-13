package br.com.jmtech.application.mapper;


import br.com.jmtech.application.dto.aluno.AlunoCreateDTO;
import br.com.jmtech.application.dto.aluno.AlunoDTO;
import br.com.jmtech.application.dto.aluno.AlunoSearchDTO;
import br.com.jmtech.application.dto.aluno.AlunoUpdateDTO;
import br.com.jmtech.infrastructure.domains.Aluno;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class AlunoMapper {

    public static final AlunoMapper INSTANCE = Mappers.getMapper(AlunoMapper.class);


    public abstract AlunoDTO toAlunoDTO(Aluno aluno);

    public abstract Aluno toAluno(AlunoCreateDTO alunoCreateDTO, @MappingTarget Aluno newClient, @Context AlunoContext alunoContext);

    public abstract Aluno toAluno(AlunoUpdateDTO clientUpdateDTO, @MappingTarget Aluno existAluno, @Context AlunoContext alunoContext);

    public abstract AlunoSearchDTO toAlunoSearchDTO(Aluno aluno);


    public static class AlunoContext {

        private final Long idAluno;

        public AlunoContext(Long idAluno) {
            this.idAluno = idAluno;
        }

    }
}
