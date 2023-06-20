package com.impacta.messagingsrv;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:application.properties")
public class MessagingsrvConfig {

    @Value("${messagingsrv.inserirproduto.request.queue.name}")
    public final static String NOME_FILA_INSERIR_PRODUTO_REQ = "com.impacta.messagingSrv.inserirProdutoQueue.req";

    @Value("${messagingsrv.inserirproduto.request.queue.durable}")
    public final static Boolean FILA_INSERIR_PRODUTO_REQ_DURAVEL = false;

    @Value("${messagingsrv.inserirproduto.response.queue.name}")
    public final static String NOME_FILA_INSERIR_PRODUTO_RESP = "com.impacta.messagingSrv.inserirProdutoQueue.resp";

    @Value("${messagingsrv.inserirproduto.response.queue.durable}")
    public final static Boolean FILA_INSERIR_PRODUTO_RESP_DURAVEL = false;

    @Bean
    public Queue InserirProdutoRequestQueue() {
        return new Queue(NOME_FILA_INSERIR_PRODUTO_REQ, FILA_INSERIR_PRODUTO_REQ_DURAVEL);
    }

    @Bean
    public Queue InserirProdutoResponseQueue() {
        return new Queue(NOME_FILA_INSERIR_PRODUTO_RESP, FILA_INSERIR_PRODUTO_RESP_DURAVEL);
    }
}
