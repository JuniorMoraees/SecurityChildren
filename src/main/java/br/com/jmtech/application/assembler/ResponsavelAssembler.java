package br.com.jmtech.application.assembler;


import br.com.jmtech.adapters.repository.AlunoRepository;
import br.com.jmtech.adapters.repository.ResponsavelAlunoRepository;
import br.com.jmtech.adapters.repository.TipoTelefoneRepository;
import br.com.jmtech.application.dto.PaginatedAnswerDTO;
import br.com.jmtech.application.dto.responsavel.ResponsavelDTO;
import br.com.jmtech.application.dto.responsavel.ResponsavelSearchDTO;
import br.com.jmtech.application.dto.responsavel.ResponsavelUpdateDTO;
import br.com.jmtech.application.dto.responsavel.ResponsavelCreateDTO;

import br.com.jmtech.application.mapper.AlunoMapper;
import br.com.jmtech.application.mapper.ResponsavelAlunoMapper;

import br.com.jmtech.infrastructure.persistence.entity.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ResponsavelAssembler {

//    public ResponsavelAluno toResponsavel(ResponsavelAlunoCreateDTO responsavelAlunoCreateDTO) {
//        return ResponsavelAlunoMapper.INSTANCE.toResponsavel(responsavelAlunoCreateDTO, ResponsavelAluno.builder().build(), new ResponsavelAlunoMapper.ResponsavelContext(null));
//    }

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TipoTelefoneRepository tipoTelefoneRepository;

    @Autowired
    private ResponsavelAlunoRepository responsavelAlunoRepository;

    @Autowired
    private AlunoMapper alunoMapper;

    public Responsavel toResponsavel(ResponsavelCreateDTO dto) {
        if (dto == null) return null;

        Responsavel newResponsavel = new Responsavel();
        newResponsavel.setNome(dto.getNome());
        newResponsavel.setCpf(dto.getCpf());
        List<Telefone> telefones = telefoneCreateDTOListToTelefoneList(dto.getTelefones());
        newResponsavel.setTelefones(telefones);

        return newResponsavel;
    }

    public List<Telefone> telefoneCreateDTOListToTelefoneList(List<ResponsavelCreateDTO.TelefoneCreateDTO> dtos) {
        if (dtos == null) return Collections.emptyList();

        return dtos.stream().map(dto -> {
            Telefone telefone = new Telefone();
            telefone.setNumero(dto.getNumero());
            if (dto.getTipoTelefoneId() == null || dto.getTipoTelefoneId() == 0) {
                throw new IllegalArgumentException("ID do TipoTelefone não pode ser 0.");
            }
            TipoTelefone tipoTelefone = tipoTelefoneRepository.findById(dto.getTipoTelefoneId())
                    .orElseThrow(() -> new EntityNotFoundException("TipoTelefone não encontrado com id: " + dto.getTipoTelefoneId()));
            telefone.setTipoTelefone(tipoTelefone);

            return telefone;
        }).collect(Collectors.toList());
    }

    public void criarVinculosAlunoResponsavel(List<Long> alunosIds, Responsavel responsavel) {
        if (alunosIds == null || alunosIds.isEmpty()) return;
        for (Long alunoId : alunosIds) {
            Aluno aluno = alunoRepository.findById(alunoId)
                    .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado: " + alunoId));
            boolean exists = responsavelAlunoRepository.existsByResponsavelAndAluno(responsavel, aluno);
            if (!exists) {
                ResponsavelAluno responsavelAluno = ResponsavelAluno.builder()
                        .responsavel(responsavel)
                        .aluno(aluno)
                        .build();
                responsavelAlunoRepository.save(responsavelAluno);
            }
        }
    }

    public Responsavel toResponsavel(ResponsavelUpdateDTO responsavelUpdateDTO, Responsavel existResponsavel, long idResponsavel) {
        return ResponsavelAlunoMapper.INSTANCE.toResponsavel(responsavelUpdateDTO, existResponsavel, new ResponsavelAlunoMapper.ResponsavelContext(idResponsavel));
    }

//    public List<ResponsavelAlunoDTO> toResponsavelDTO (List<ResponsavelAluno> responsaveis) {
//        return ResponsavelAlunoMapper.INSTANCE.toResponsavelDTO(responsaveis);
//    }

    public List<ResponsavelDTO> toResponsavelDTO(List<Responsavel> responsavel) {
        if ( responsavel == null ) {
            return null;
        }

        List<ResponsavelDTO> list = new ArrayList<ResponsavelDTO>( responsavel.size() );
        for ( Responsavel responsavelAluno : responsavel ) {
            list.add( responsavelAlunoToResponsavelAlunoDTO( responsavelAluno ) );
        }

        return list;
    }

    protected ResponsavelDTO responsavelAlunoToResponsavelAlunoDTO(Responsavel responsavel) {
        if (responsavel == null) {
            return null;
        }

        ResponsavelDTO.ResponsavelDTOBuilder responsavelAlunoDTO = ResponsavelDTO.builder();
        responsavelAlunoDTO.id(responsavel.getId());
        responsavelAlunoDTO.nome(responsavel.getNome());
        responsavelAlunoDTO.cpf(responsavel.getCpf());
        List<ResponsavelAluno> vinculos = responsavelAlunoRepository.findByResponsavel(responsavel);
        List<Aluno> alunos = vinculos.stream()
                .map(ResponsavelAluno::getAluno)
                .collect(Collectors.toList());
        responsavelAlunoDTO.alunos(alunoMapper.toAlunoDTO(alunos));
        responsavelAlunoDTO.telefones(telefoneListToTelefoneList(responsavel.getTelefones()));
        if (responsavel.getFoto() != null) {
            responsavelAlunoDTO.foto(responsavel.getFoto());
        }

        return responsavelAlunoDTO.build();
    }

    protected List<ResponsavelDTO.Telefone> telefoneListToTelefoneList(List<Telefone> list) {
        if ( list == null ) {
            return null;
        }

        List<ResponsavelDTO.Telefone> list1 = new ArrayList<ResponsavelDTO.Telefone>( list.size() );
        for ( Telefone telefone : list ) {
            list1.add( telefoneToTelefone( telefone ) );
        }

        return list1;
    }
    protected ResponsavelDTO.Telefone telefoneToTelefone(Telefone telefone) {
        if ( telefone == null ) {
            return null;
        }

        ResponsavelDTO.Telefone.TelefoneBuilder telefone1 = ResponsavelDTO.Telefone.builder();

        telefone1.tipoTelefone( tipoTelefoneToTipoTelefone( telefone.getTipoTelefone() ) );
        telefone1.numero( telefone.getNumero() );

        return telefone1.build();
    }

    protected ResponsavelDTO.TipoTelefone tipoTelefoneToTipoTelefone(TipoTelefone tipoTelefone) {
        if ( tipoTelefone == null ) {
            return null;
        }

        ResponsavelDTO.TipoTelefone.TipoTelefoneBuilder tipoTelefone1 = ResponsavelDTO.TipoTelefone.builder();

        return tipoTelefone1.build();
    }

    public ResponsavelSearchDTO toResponsavelSearchDTO(Responsavel responsavel) {
        return ResponsavelAlunoMapper.INSTANCE.toResponsavelSearchDTO(responsavel);
    }

    public PaginatedAnswerDTO<ResponsavelDTO> toResponsavelDTO(PaginatedAnswerDTO<Responsavel> responsaveis) {
        return PaginatedAnswerDTO.<ResponsavelDTO>builder()
                .pageMetaData(responsaveis.getPageMetaData())
                .answerContent(toResponsavelDTO(responsaveis.getAnswerContent()))
                .build();
    }
}
