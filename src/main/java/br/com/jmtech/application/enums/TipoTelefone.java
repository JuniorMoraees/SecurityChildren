package br.com.jmtech.application.enums;

import lombok.Getter;

@Getter
public enum TipoTelefone {
    FIXO("Telefone Fixo"),
    MOVEL("Telefone Movel"),
    COMERCIAL("Telefone Trabalho");

    private final String descricao;

    TipoTelefone(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
