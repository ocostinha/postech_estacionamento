package com.fiap.postech.estacionamento.controller;

import com.fiap.postech.estacionamento.commoms.mappers.ActuationAreaValueMapper;
import com.fiap.postech.estacionamento.controller.dto.ActuationAreaValueRequestDTO;
import com.fiap.postech.estacionamento.controller.dto.ActuationAreaValueResponseDTO;
import com.fiap.postech.estacionamento.core.service.ActuationAreaValueService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/actuationAreaValues")
@AllArgsConstructor
public class ActuationAreaValueController {

    @Autowired
    private final ActuationAreaValueService service;

    @Autowired
    private final ActuationAreaValueMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ActuationAreaValueResponseDTO create(@Valid @RequestBody ActuationAreaValueRequestDTO request) {
        return mapper.toResponse(
                service.create(
                        mapper.toDomain(request)
                )
        );
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ActuationAreaValueResponseDTO getById(@PathVariable Long id) {
        return mapper.toResponse(service.getById(id));
    }

    @GetMapping("/actuationArea")
    @ResponseStatus(HttpStatus.OK)
    public List<ActuationAreaValueResponseDTO> getByActuationArea(@RequestParam Long idActuationArea) {
        return service.getByActuationArea(idActuationArea).stream().map(mapper::toResponse).toList();
    }

    @PatchMapping("/{id}")
    public ActuationAreaValueResponseDTO updateFinalDate(@PathVariable Long id,
                                                         @RequestParam LocalDateTime finalDate) {
        return mapper.toResponse(service.updateFinalDate(id, finalDate));
    }
}
