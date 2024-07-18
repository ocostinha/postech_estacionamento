package com.fiap.postech.estacionamento.controller.dto;


import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;

public record FormaPagamentoDTO(
        Long id,
        @NotBlank(message = " Forma de pagamento é obrigatória")
        String descricao,
        boolean aceitaValoresPreEstabelecidos,
        List<Double> listaValores,
        boolean ativo,
        LocalDateTime dataCriacao,
        LocalDateTime dataUltimaAtualizacao
) {
}
