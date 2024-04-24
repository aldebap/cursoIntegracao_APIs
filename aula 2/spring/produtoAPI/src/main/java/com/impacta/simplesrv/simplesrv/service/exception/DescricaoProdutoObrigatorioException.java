package com.impacta.simplesrv.simplesrv.service.exception;

public class DescricaoProdutoObrigatorioException extends RuntimeException {

    public DescricaoProdutoObrigatorioException() {
        super("A descricao do produto é campo obrigatorio");
    }
}
