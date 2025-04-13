package br.com.jmtech.interfaceAdapters.gateway;

import br.com.jmtech.infrastructure.domains.Aluno;

public interface AlunoGateway {

    Aluno findByIdOrElseThrow(Long idAluno);

    Aluno findByQrCodeOrElseThrow(String qrCode);


    Aluno createAluno(Aluno aluno);
}
