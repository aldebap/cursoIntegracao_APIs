package com.impacta.simplesrv.simplesrv.service;

import org.springframework.stereotype.Service;

import com.impacta.simplesrv.simplesrv.domain.Hello;

@Service
public class HelloSvc {

    public Hello GetHelloMessage() {
        return new Hello("Hello Imapacta !");
    }
}
