package com.fiap.postech.estacionamento.controller;

import com.fiap.postech.estacionamento.commoms.mappers.PaymentModeMapper;
import com.fiap.postech.estacionamento.controller.dto.PaymentModeDTO;
import com.fiap.postech.estacionamento.core.service.PaymentModeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paymentModes")
@AllArgsConstructor
public class PaymentModeController {

    @Autowired
    private final PaymentModeService PaymentModeService;

    @Autowired
    private final PaymentModeMapper mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PaymentModeDTO> findAll() {
        return PaymentModeService.findAll().stream().map(mapper::toDto).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentModeDTO create(@Valid @RequestBody PaymentModeDTO request) {
        return mapper.toDto(PaymentModeService.create(mapper.toDomain(request)));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PaymentModeDTO findById(@PathVariable Long id) {
        return mapper.toDto(PaymentModeService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PaymentModeDTO disable(@PathVariable Long id) {
        return mapper.toDto(PaymentModeService.disable(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PaymentModeDTO update(@PathVariable Long id, @RequestBody PaymentModeDTO request) {
        return mapper.toDto(PaymentModeService.update(id, mapper.toDomain(request)));
    }
}
