package com.fiap.postech.estacionamento.controller;

import com.fiap.postech.estacionamento.commoms.mappers.EstacionamentoMapper;
import com.fiap.postech.estacionamento.controller.dto.EstacionamentoRequestDTO;
import com.fiap.postech.estacionamento.controller.dto.EstacionamentoResponseDTO;
import com.fiap.postech.estacionamento.core.domain.Estacionamento;
import com.fiap.postech.estacionamento.core.service.EstacionamentoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/estacionamento")
@AllArgsConstructor
public class EstacionamentoController {

    @Autowired
    private final EstacionamentoService service;

    @Autowired
    private final EstacionamentoMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registrarEstacionamento(@Valid @RequestBody String placa) {
        service.verificarEstacionamentoEmAberto(placa);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstacionamentoResponseDTO registrarEstacionamento(@Valid @RequestBody EstacionamentoRequestDTO request) {
        return mapper.toDto(service.registrarEstacionamento(mapper.toDomain(request)));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EstacionamentoResponseDTO buscarEstacionamentoPorId(@PathVariable Long id) {
        return mapper.toResponse(service.consultarEstacionamentoPorId(id));
    }

    @GetMapping("/placa")
    @ResponseStatus(HttpStatus.OK)
    public List<EstacionamentoResponseDTO> buscarEstacionamentosPorPlaca(@RequestParam String placa) {
        return service.consultarEstacionamentosPorPlaca(placa).stream().map(mapper::toResponse).toList();
    }
}
