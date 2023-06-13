package com.impacta.simplesrv.simplesrv.domain;

import java.util.UUID;

public class Produto {
    private UUID id;
    private String codigo;
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
        this.id = UUID.randomUUID(); // generate a new UUID-v4
        this.codigo = "";
        this.descricao = "";
    }

    public Produto(UUID id, String codigo, String descricao) {
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
    }
}
