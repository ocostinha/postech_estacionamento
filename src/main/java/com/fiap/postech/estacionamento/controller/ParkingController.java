package com.fiap.postech.estacionamento.controller;

import com.fiap.postech.estacionamento.commoms.mappers.ParkingMapper;
import com.fiap.postech.estacionamento.controller.dto.ParkingRequestDTO;
import com.fiap.postech.estacionamento.controller.dto.ParkingResponseDTO;
import com.fiap.postech.estacionamento.core.service.ParkingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/parking")
public class ParkingController {
    @Autowired
    private ParkingService service;

    @Autowired
    private ParkingMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParkingResponseDTO register(@Valid @RequestBody ParkingRequestDTO request) {
        return mapper.toDto(
                service.register(
                        mapper.toDomain(request)
                )
        );
    }

    @PatchMapping("/licensePlate/{licensePlate}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ParkingResponseDTO exitRegister(
            @PathVariable String licensePlate,
            @RequestParam LocalDateTime finalParkingDate
    ) {
        return mapper.toDto(
                service.exitRegister(licensePlate, finalParkingDate)
        );
    }
}
