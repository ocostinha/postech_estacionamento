package com.fiap.postech.estacionamento.commoms.exception;

public class ControllerNotFoundException extends RuntimeException{
    public ControllerNotFoundException(String message) {
        super(message);
    }
}
