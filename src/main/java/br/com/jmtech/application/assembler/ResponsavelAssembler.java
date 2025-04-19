package br.com.jmtech.application.assembler;


import br.com.jmtech.adapters.repository.AlunoRepository;
import br.com.jmtech.application.dto.responsavel.ResponsavelAlunoDTO;
import br.com.jmtech.application.dto.responsavel.ResponsavelAlunoSearchDTO;
import br.com.jmtech.application.dto.responsavel.ResponsavelAlunoUpdateDTO;
import br.com.jmtech.application.dto.responsavel.ResponsavelAlunoCreateDTO;

import br.com.jmtech.application.mapper.AlunoMapper;
import br.com.jmtech.application.mapper.ResponsavelAlunoMapper;

import br.com.jmtech.infrastructure.persistence.entity.Aluno;
import br.com.jmtech.infrastructure.persistence.entity.Responsavel;
import br.com.jmtech.infrastructure.persistence.entity.Telefone;
import br.com.jmtech.infrastructure.persistence.entity.TipoTelefone;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
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
    private AlunoMapper alunoMapper;

    public Responsavel toResponsavel(ResponsavelAlunoCreateDTO dto) {
        if (dto == null) return null;

        Responsavel newResponsavel = new Responsavel();

        newResponsavel.setNome(dto.getNome());
        newResponsavel.setCpf(dto.getCpf());

        List<Telefone> telefones = telefoneCreateDTOListToTelefoneList(dto.getTelefones());
        newResponsavel.setTelefones(telefones);

        if (dto.getAlunosIds() != null && !dto.getAlunosIds().isEmpty()) {
            List<Aluno> alunos = alunoRepository.findAllById(dto.getAlunosIds());
            newResponsavel.setAlunos(alunos);
        } else {
            newResponsavel.setAlunos(Collections.emptyList());
        }

        return newResponsavel;
    }

    public static List<Telefone> telefoneCreateDTOListToTelefoneList(List<ResponsavelAlunoCreateDTO.TelefoneCreateDTO> dtos) {
        if (dtos == null) return Collections.emptyList();

        return dtos.stream().map(dto -> {
            Telefone telefone = new Telefone();
            telefone.setNumero(dto.getNumero());
            return telefone;
        }).collect(Collectors.toList());
    }

    public Responsavel toResponsavel(ResponsavelAlunoUpdateDTO responsavelUpdateDTO, Responsavel existResponsavel, long idResponsavel) {
        return ResponsavelAlunoMapper.INSTANCE.toResponsavel(responsavelUpdateDTO, existResponsavel, new ResponsavelAlunoMapper.ResponsavelContext(idResponsavel));
    }

//    public List<ResponsavelAlunoDTO> toResponsavelDTO (List<ResponsavelAluno> responsaveis) {
//        return ResponsavelAlunoMapper.INSTANCE.toResponsavelDTO(responsaveis);
//    }

    public List<ResponsavelAlunoDTO> toResponsavelDTO(List<Responsavel> responsavel) {
        if ( responsavel == null ) {
            return null;
        }

        List<ResponsavelAlunoDTO> list = new ArrayList<ResponsavelAlunoDTO>( responsavel.size() );
        for ( Responsavel responsavelAluno : responsavel ) {
            list.add( responsavelAlunoToResponsavelAlunoDTO( responsavelAluno ) );
        }

        return list;
    }

    protected ResponsavelAlunoDTO responsavelAlunoToResponsavelAlunoDTO(Responsavel responsavel) {
        if (responsavel == null) {
            return null;
        }

        ResponsavelAlunoDTO.ResponsavelAlunoDTOBuilder responsavelAlunoDTO = ResponsavelAlunoDTO.builder();

        responsavelAlunoDTO.nome(responsavel.getNome());
        responsavelAlunoDTO.cpf(responsavel.getCpf());

        if (responsavel.getAlunos() != null && !responsavel.getAlunos().isEmpty()) {
            responsavel.getAlunos().size();
            responsavelAlunoDTO.alunos(alunoMapper.toAlunoDTO(responsavel.getAlunos()));
        } else {
            responsavelAlunoDTO.alunos(Collections.emptyList());
        }

        responsavelAlunoDTO.telefones(telefoneListToTelefoneList(responsavel.getTelefones()));
        byte[] foto = responsavel.getFoto();
        if (foto != null) {
            responsavelAlunoDTO.foto(Arrays.copyOf(foto, foto.length));
        }

        return responsavelAlunoDTO.build();
    }
    protected List<ResponsavelAlunoDTO.Telefone> telefoneListToTelefoneList(List<Telefone> list) {
        if ( list == null ) {
            return null;
        }

        List<ResponsavelAlunoDTO.Telefone> list1 = new ArrayList<ResponsavelAlunoDTO.Telefone>( list.size() );
        for ( Telefone telefone : list ) {
            list1.add( telefoneToTelefone( telefone ) );
        }

        return list1;
    }
    protected ResponsavelAlunoDTO.Telefone telefoneToTelefone(Telefone telefone) {
        if ( telefone == null ) {
            return null;
        }

        ResponsavelAlunoDTO.Telefone.TelefoneBuilder telefone1 = ResponsavelAlunoDTO.Telefone.builder();

        telefone1.tipoTelefone( tipoTelefoneToTipoTelefone( telefone.getTipoTelefone() ) );
        telefone1.numero( telefone.getNumero() );

        return telefone1.build();
    }

    protected ResponsavelAlunoDTO.TipoTelefone tipoTelefoneToTipoTelefone(TipoTelefone tipoTelefone) {
        if ( tipoTelefone == null ) {
            return null;
        }

        ResponsavelAlunoDTO.TipoTelefone.TipoTelefoneBuilder tipoTelefone1 = ResponsavelAlunoDTO.TipoTelefone.builder();

        return tipoTelefone1.build();
    }

    public ResponsavelAlunoSearchDTO toResponsavelSearchDTO(Responsavel responsavel) {
        return ResponsavelAlunoMapper.INSTANCE.toResponsavelSearchDTO(responsavel);
    }
}
