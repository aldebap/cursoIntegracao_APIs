package com.impacta.simplesrv.simplesrv.domain;

public class Hello {
    private String message = "Hello Java World !";

    public Hello() {
    }

    public Hello(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
