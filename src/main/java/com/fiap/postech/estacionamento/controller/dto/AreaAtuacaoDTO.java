package com.fiap.postech.estacionamento.controller.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record AreaAtuacaoDTO(
        Long id,
        @NotBlank(message = "Nome da área é obrigatório")
        String nome,
        @NotBlank(message = "Cidade é obrigatória")
        String cidade,
        @NotBlank(message = "Estado é obrigatório")
        String estado,
        boolean active,
        LocalDateTime creationDate,
        LocalDateTime dataUltimaModificacao
) {

}

