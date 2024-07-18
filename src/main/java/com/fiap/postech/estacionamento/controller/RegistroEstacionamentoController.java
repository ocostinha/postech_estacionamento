package com.fiap.postech.estacionamento.controller;

import com.fiap.postech.estacionamento.commoms.mappers.RegistroEstacionamentoMapper;
import com.fiap.postech.estacionamento.controller.dto.RegistroEstacionamentoRequestDTO;
import com.fiap.postech.estacionamento.controller.dto.RegistroEstacionamentoResponseDTO;
import com.fiap.postech.estacionamento.core.service.RegistroEstacionamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registro-estacionamento")
public class RegistroEstacionamentoController {
    @Autowired
    private RegistroEstacionamentoService service;

    @Autowired
    private RegistroEstacionamentoMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RegistroEstacionamentoResponseDTO registrarEstacionamento(
            @RequestBody RegistroEstacionamentoRequestDTO request
    ) {
        return mapper.toDto(
                service.registrarEstacionamento(
                        mapper.toDomain(request)
                )
        );
    }
}
