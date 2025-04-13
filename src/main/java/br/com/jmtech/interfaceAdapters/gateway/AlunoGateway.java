package br.com.jmtech.interfaceAdapters.gateway;

import br.com.jmtech.infrastructure.domains.Aluno;

import java.util.List;

public interface AlunoGateway {

    Aluno findByIdOrElseThrow(Long idAluno);

    Aluno findByQrCodeOrElseThrow(String qrCode);


    Aluno createAluno(Aluno aluno);

    List<Aluno> findAll();

    Aluno updateAluno(Aluno alunoToUpdate);
}
