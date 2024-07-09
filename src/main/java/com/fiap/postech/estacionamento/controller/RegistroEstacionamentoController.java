package com.fiap.postech.estacionamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fiap.postech.estacionamento.controller.dto.RegistroEstacionamentoRequestDTO;
import com.fiap.postech.estacionamento.controller.dto.RegistroEstacionamentoResponseDTO;
import com.fiap.postech.estacionamento.core.service.RegistroEstacionamentoService;

@RestController
@RequestMapping("/registro-estacionamento")
public class RegistroEstacionamentoController {
    @Autowired
    private RegistroEstacionamentoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RegistroEstacionamentoResponseDTO registrarEstacionamento(@RequestBody RegistroEstacionamentoRequestDTO request) {
        try {
            return service.registrarEstacionamento(request);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }
}
