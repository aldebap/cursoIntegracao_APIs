package com.impacta.messagingsrv;

import javax.xml.crypto.Data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.impacta.messagingsrv.domain.Produto;
import com.impacta.messagingsrv.service.ProdutoSvc;
import com.impacta.messagingsrv.service.exception.CodigoProdutoObrigatorioException;
import com.impacta.messagingsrv.service.exception.DescricaoProdutoObrigatorioException;

@SpringBootApplication
public class MessagingsrvApplication {

	private final RabbitTemplate rabbitTemplate;
	private final ProdutoSvc produtoService;

	public MessagingsrvApplication(RabbitTemplate rabbitTemplate, ProdutoSvc produtoService) {
		this.rabbitTemplate = rabbitTemplate;
		this.produtoService = produtoService;
	}

	@RabbitListener(queues = { MessagingsrvConfig.NOME_FILA_INSERIR_PRODUTO_REQ })
	public void listenInserirProdutoReqQueue(String requestPayload) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Produto produtoReq = objectMapper.readValue(requestPayload, Produto.class);
			Produto produtoResp = this.produtoService.InserirProduto(produtoReq);
			ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

			rabbitTemplate.convertAndSend(MessagingsrvConfig.NOME_FILA_INSERIR_PRODUTO_RESP,
					objectWriter.writeValueAsString(produtoResp));
		} catch (JsonProcessingException e) {
			rabbitTemplate.convertAndSend(MessagingsrvConfig.NOME_FILA_INSERIR_PRODUTO_RESP, e.getMessage());
		} catch (CodigoProdutoObrigatorioException e) {
			rabbitTemplate.convertAndSend(MessagingsrvConfig.NOME_FILA_INSERIR_PRODUTO_RESP, e.getMessage());
		} catch (DescricaoProdutoObrigatorioException e) {
			rabbitTemplate.convertAndSend(MessagingsrvConfig.NOME_FILA_INSERIR_PRODUTO_RESP, e.getMessage());
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(MessagingsrvApplication.class, args);
	}
}
