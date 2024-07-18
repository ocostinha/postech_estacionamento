package com.fiap.postech.estacionamento.controller.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class EstacionamentoRequestDTO {
    @NotBlank
    private String placa;

    @NotBlank
    private Long idAreaAtuacao;

    @NotBlank
    private LocalDateTime inicioEstacionamento;

}