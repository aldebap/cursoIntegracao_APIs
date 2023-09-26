package com.impacta.simplesrv.simplesrv.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.impacta.simplesrv.simplesrv.domain.Produto;
import com.impacta.simplesrv.simplesrv.service.ProdutoSvc;

@Controller
public class ProdutoQLCtrl {
    private final ProdutoSvc produtoService;

    public ProdutoQLCtrl(ProdutoSvc produtoService) {
        this.produtoService = produtoService;
    }

    @QueryMapping
    public Produto produtoPorCodigo(@Argument String codigo) {
        System.out.printf("GraphQL query: %s\n", codigo);
        return produtoService.RecuperarProduto(codigo);
    }
}
