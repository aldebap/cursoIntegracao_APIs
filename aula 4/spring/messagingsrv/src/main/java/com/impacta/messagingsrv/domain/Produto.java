package com.impacta.messagingsrv.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

public class Produto {
    private UUID id;
    @JsonProperty
    private String codigo;
    @JsonProperty
    private String descricao;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Produto() {
    }

    public Produto(UUID id, String codigo, String descricao) {
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
    }
}
