package com.impacta.messagingsrv.service.exception;

public class DescricaoProdutoObrigatorioException extends RuntimeException {

    public DescricaoProdutoObrigatorioException() {
        super("A descricao do produto é campo obrigatorio");
    }
}
