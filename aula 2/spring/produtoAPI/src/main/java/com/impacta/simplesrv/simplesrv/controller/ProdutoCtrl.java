package com.impacta.simplesrv.simplesrv.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.impacta.simplesrv.simplesrv.domain.Produto;
import com.impacta.simplesrv.simplesrv.service.ProdutoSvc;
import com.impacta.simplesrv.simplesrv.service.exception.CodigoProdutoObrigatorioException;
import com.impacta.simplesrv.simplesrv.service.exception.DescricaoProdutoObrigatorioException;

@RestController

@RequestMapping("/api/v1/produto")
public class ProdutoCtrl {

    private final ProdutoSvc produtoService;

    public ProdutoCtrl(ProdutoSvc produtoService) {
        this.produtoService = produtoService;
    }

    public interface ProdutoCtrlResponse {}

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class ProdutoResponse implements ProdutoCtrlResponse {
        private String codigo;
        private String descricao;
        private String SKU;
        private String categoria;

        public ProdutoResponse() {
            this.codigo = "";
            this.descricao = "";
            this.SKU = "";
            this.categoria = "";
        }

        public ProdutoResponse(Produto produto) {
            this.codigo = produto.getCodigo();
            this.descricao = produto.getDescricao();
            this.SKU = produto.getSKU();
            this.categoria = produto.getCategoria();
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
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class MessageResponse implements ProdutoCtrlResponse {
        private String mensagem;

        public MessageResponse() {
            this.mensagem = null;
        }

        public MessageResponse(String mensagem) {
            this.mensagem = mensagem;
        }

        public void setMensagem(String mensagem) {
            this.mensagem = mensagem;
        }

        public String getMensagem() {
            return this.mensagem;
        }
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProdutoCtrlResponse> postProduto(@RequestBody Produto produto) {
        try {
            Produto novoProduto = this.produtoService.InserirProduto(produto);

            return new ResponseEntity<ProdutoCtrlResponse>(new ProdutoResponse(novoProduto), HttpStatus.CREATED);
        } catch (CodigoProdutoObrigatorioException e) {
            return new ResponseEntity<ProdutoCtrlResponse>(new MessageResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (DescricaoProdutoObrigatorioException e) {
            return new ResponseEntity<ProdutoCtrlResponse>(new MessageResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<ProdutoCtrlResponse> getProduto(@PathVariable("codigo") String codigo) {
        Produto produto = this.produtoService.RecuperarProduto(codigo);

        if (produto == null) {
            return new ResponseEntity<ProdutoCtrlResponse>(new MessageResponse("Codigo inexistente"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ProdutoCtrlResponse>(new ProdutoResponse(produto), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Collection<ProdutoResponse>> getListaProdutos() {
        Collection<Produto> listaProdutos = this.produtoService.RecuperarListaProdutos();
        Collection<ProdutoResponse> listaProdutosResponse = new ArrayList<ProdutoResponse>();

        for (Produto produto : listaProdutos) {
            listaProdutosResponse.add(new ProdutoResponse(produto));
        }

        return new ResponseEntity<Collection<ProdutoResponse>>(listaProdutosResponse, HttpStatus.OK);
    }

    @PatchMapping("/{codigo}")
    public ResponseEntity<ProdutoCtrlResponse> patchProduto(@PathVariable("codigo") String codigo,
            @RequestBody Produto produto) {
        try {
            Produto produtoAux = this.produtoService.AtualizarProduto(codigo, produto);

            if (produtoAux == null) {
                return new ResponseEntity<ProdutoCtrlResponse>(new MessageResponse("Codigo inexistente"),
                        HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<ProdutoCtrlResponse>(new ProdutoResponse(produtoAux), HttpStatus.OK);
        } catch (DescricaoProdutoObrigatorioException e) {
            return new ResponseEntity<ProdutoCtrlResponse>(new MessageResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<ProdutoCtrlResponse> deleteProduto(@PathVariable("codigo") String codigo) {
        Produto produto = this.produtoService.ExcluirProduto(codigo);

        if (produto == null) {
            return new ResponseEntity<ProdutoCtrlResponse>(new MessageResponse("Codigo inexistente"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ProdutoCtrlResponse>(new MessageResponse(""), HttpStatus.OK);
    }
}
