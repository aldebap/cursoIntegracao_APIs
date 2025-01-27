package com.impacta.simplesrv.simplesrv.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.impacta.simplesrv.simplesrv.service.HelloSvc;

@RestController

@RequestMapping("/api")
public class HelloCtrl {
    private final HelloSvc helloService;

    public HelloCtrl() {
        this.helloService = new HelloSvc();
    }

    @GetMapping("v1/hello")
    public ResponseEntity getHelloMessage() {
        return ResponseEntity.ok(this.helloService.GetHelloMessage());
    }
}
