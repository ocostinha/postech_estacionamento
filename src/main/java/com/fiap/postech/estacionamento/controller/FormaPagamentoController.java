package com.fiap.postech.estacionamento.controller;

import com.fiap.postech.estacionamento.commoms.mappers.FormaPagamentoMapper;
import com.fiap.postech.estacionamento.controller.dto.FormaPagamentoDTO;
import com.fiap.postech.estacionamento.core.service.FormaPagamentoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formaPagamento")
@AllArgsConstructor
public class FormaPagamentoController {

    @Autowired
    private final FormaPagamentoService formaPagamentoService;

    @Autowired
    private final FormaPagamentoMapper mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FormaPagamentoDTO> findAll() {
        return formaPagamentoService.findAll().stream().map(mapper::toDto).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoDTO save(@Valid @RequestBody FormaPagamentoDTO formaPagamentoDTO) {
        return mapper.toDto(formaPagamentoService.save(mapper.toDomain(formaPagamentoDTO)));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FormaPagamentoDTO findById(@PathVariable Long id) {
        return mapper.toDto(formaPagamentoService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public FormaPagamentoDTO delete(@PathVariable Long id) {
        return mapper.toDto(formaPagamentoService.desativar(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public FormaPagamentoDTO update(@PathVariable Long id, @RequestBody FormaPagamentoDTO formaPagamentoDTO) {
        return mapper.toDto(formaPagamentoService.update(id, mapper.toDomain(formaPagamentoDTO)));
    }
}
