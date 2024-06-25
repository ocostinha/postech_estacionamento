package com.fiap.postech.estacionamento.controller;

import com.fiap.postech.estacionamento.commoms.mappers.AreaAtuacaoMapper;
import com.fiap.postech.estacionamento.controller.dto.AreaAtuacaoDTO;
import com.fiap.postech.estacionamento.core.service.AreaAtuacaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/areaAtuacao")
@AllArgsConstructor
public class AreaAtuacaoController {
    @Autowired
    private final AreaAtuacaoService areaAtuacaoService;

    @Autowired
    private final AreaAtuacaoMapper mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AreaAtuacaoDTO> findAll() {
        return areaAtuacaoService.findAll().stream().map(mapper::toDto).toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AreaAtuacaoDTO findById(@PathVariable Long id) {
        return mapper.toDto(areaAtuacaoService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AreaAtuacaoDTO save(@Valid @RequestBody AreaAtuacaoDTO areaAtuacaoDTO) {
        return mapper.toDto(areaAtuacaoService.save(mapper.toDomain(areaAtuacaoDTO)));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AreaAtuacaoDTO update(@PathVariable Long id, @RequestParam String nomeArea) {
       return mapper.toDto(areaAtuacaoService.update(id, nomeArea));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AreaAtuacaoDTO delete(@PathVariable Long id) {
        return mapper.toDto(areaAtuacaoService.desativar(id));
    }
}
