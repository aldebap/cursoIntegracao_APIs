package com.impacta.simplesrv.simplesrv.domain;

import java.util.UUID;

public class Produto {
    private UUID id;
    private String codigo;
    private String descricao;

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return this.id;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
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
