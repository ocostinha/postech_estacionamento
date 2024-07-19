package com.fiap.postech.estacionamento.controller;

import com.fiap.postech.estacionamento.commoms.mappers.EstacionamentoMapper;
import com.fiap.postech.estacionamento.controller.dto.EstacionamentoRequestDTO;
import com.fiap.postech.estacionamento.controller.dto.EstacionamentoResponseDTO;
import com.fiap.postech.estacionamento.core.service.EstacionamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estacionamento")
public class EstacionamentoController {
    @Autowired
    private EstacionamentoService service;

    @Autowired
    private EstacionamentoMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstacionamentoResponseDTO registrarEstacionamento(
            @RequestBody EstacionamentoRequestDTO request
    ) {
        return mapper.toDto(
                service.registrar(
                        mapper.toDomain(request)
                )
        );
    }
}