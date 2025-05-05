package br.com.jmtech.adapters.gateway;

import br.com.jmtech.application.dto.PaginatedAnswerDTO;
import br.com.jmtech.infrastructure.persistence.entity.Aluno;

import java.util.List;

public interface AlunoGateway {

    Aluno findByIdOrElseThrow(Long idAluno);

//    Aluno findByQrCodeOrElseThrow(String qrCode);


    Aluno createAluno(Aluno aluno);

    PaginatedAnswerDTO<Aluno> findAll(String nome, Integer page, Integer pageSize);

    Aluno updateAluno(Aluno alunoToUpdate);

    void delete(Long idAluno);

    List<Aluno> findBynome(String nome);
}
