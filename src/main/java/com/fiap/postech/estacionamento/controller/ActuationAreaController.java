package com.fiap.postech.estacionamento.controller;

import com.fiap.postech.estacionamento.commoms.mappers.ActuationAreaMapper;
import com.fiap.postech.estacionamento.controller.dto.ActuationAreaRequestDTO;
import com.fiap.postech.estacionamento.controller.dto.ActuationAreaResponseDTO;
import com.fiap.postech.estacionamento.core.service.ActuationAreaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/actuationAreas")
@AllArgsConstructor
public class ActuationAreaController {
    @Autowired
    private final ActuationAreaService actuationAreaService;

    @Autowired
    private final ActuationAreaMapper mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ActuationAreaResponseDTO> findAll() {
        return actuationAreaService.findAll().stream().map(mapper::toResponse).toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ActuationAreaResponseDTO findById(@PathVariable Long id) {
        return mapper.toResponse(actuationAreaService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ActuationAreaResponseDTO create(@Valid @RequestBody ActuationAreaRequestDTO actuationAreaRequestDTO) {
        return mapper.toResponse(actuationAreaService.create(mapper.toDomain(actuationAreaRequestDTO)));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ActuationAreaResponseDTO update(@PathVariable Long id, @RequestParam String descriptionArea) {
       return mapper.toResponse(actuationAreaService.update(id, descriptionArea));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ActuationAreaResponseDTO disable(@PathVariable Long id) {
        return mapper.toResponse(actuationAreaService.disable(id));
    }
}
