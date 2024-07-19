package com.fiap.postech.estacionamento.controller.dto;


import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record FormaPagamentoDTO(
        Long id,
        @NotBlank(message = "Forma de pagamento é obrigatória")
        String descricao,
        Boolean aceitaValorPreEstabelecido,
        Double valor,
        Boolean ativo,
        LocalDateTime dataCriacao,
        LocalDateTime dataUltimaAtualizacao
) {
}
