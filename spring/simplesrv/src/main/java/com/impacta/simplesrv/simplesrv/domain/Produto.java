package com.impacta.simplesrv.simplesrv.domain;

import java.util.UUID;

public class Produto {
    private UUID id;
    private String codigo;
    private String descricao;
    private String SKU;
    private String categoria;

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

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String sKU) {
        this.SKU = sKU;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public Produto() {
        this.id = UUID.randomUUID(); // generate a new UUID-v4
        this.codigo = "";
        this.descricao = "";
        this.SKU = "";
        this.categoria = "";
    }

    public Produto(UUID id, String codigo, String descricao, String sKU, String categoria) {
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
        this.SKU = sKU;
        this.categoria = categoria;
    }
}
