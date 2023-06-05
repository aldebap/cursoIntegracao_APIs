package com.impacta.simplesrv.simplesrv.service;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.impacta.simplesrv.simplesrv.domain.Produto;
import com.impacta.simplesrv.simplesrv.service.exception.CodigoProdutoObrigatorioException;
import com.impacta.simplesrv.simplesrv.service.exception.DescricaoProdutoObrigatorioException;

@Service
public class ProdutoSvc {

    private final Map<String, Produto> dados;

    public ProdutoSvc(Map<String, Produto> dados) {
        this.dados = dados;
    }

    // metodo para inserir um novo produto
    public Produto InserirProduto(Produto produto)
            throws CodigoProdutoObrigatorioException, DescricaoProdutoObrigatorioException {
        produto.setId(UUID.randomUUID());

        if (produto.getCodigo() == null || produto.getCodigo().isEmpty()) {
            throw new CodigoProdutoObrigatorioException();
        }

        if (produto.getDescricao() == null || produto.getDescricao().isEmpty()) {
            throw new DescricaoProdutoObrigatorioException();
        }

        // implementacao idempotente
        if (this.dados.containsKey(produto.getCodigo())) {
            produto = this.dados.get(produto.getCodigo());
        } else {
            this.dados.put(produto.getCodigo(), produto);
        }

        return produto;
    }

    // metodo para buscar um produto pelo codigo
    public Produto RecuperarProduto(String codigo) {
        if (!this.dados.containsKey(codigo)) {
            return null;
        }

        return this.dados.get(codigo);
    }

    // metodo para retornar uma lista com todos os produtos
    public Collection<Produto> RecuperarListaProdutos() {
        return this.dados.values();
    }

    // metodo para atualizar um produto pelo codigo
    public Produto AtualizarProduto(String codigo, Produto produto) throws DescricaoProdutoObrigatorioException {
        if (!this.dados.containsKey(codigo)) {
            return null;
        }

        if (produto.getDescricao() == null || produto.getDescricao().isEmpty()) {
            throw new DescricaoProdutoObrigatorioException();
        }

        // atualiza apenas os campos recebidos no payload
        Produto produtoAux = this.dados.get(codigo);

        if (produto.getDescricao() != null && !produto.getDescricao().isEmpty()) {
            produtoAux.setDescricao(produto.getDescricao());
        }

        this.dados.replace(codigo, produtoAux);

        return produtoAux;
    }

    // metodo para excluir um produto pelo codigo
    public Produto ExcluirProduto(String codigo) {
        if (!this.dados.containsKey(codigo)) {
            return null;
        }

        Produto produto = this.dados.get(codigo);

        this.dados.remove(codigo);

        return produto;
    }
}
