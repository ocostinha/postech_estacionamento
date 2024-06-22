package com.fiap.postech.estacionamento.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.Date;

public record AreaAtuacaoDTO(
        Long id,
        @NotBlank(message = "Nome da área é obrigatório")
        String nome,
        @NotBlank(message = "Cidade é obrigatória")
        String cidade,
        @NotBlank(message = "Estado é obrigatório")
        String estado,
        boolean ativo,
        LocalDateTime dataCriacao,
        LocalDateTime dataUltimaModificacao
) {

}

