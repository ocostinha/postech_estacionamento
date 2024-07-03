package com.fiap.postech.estacionamento.core.domain;

import java.time.LocalDateTime;
import java.util.List;

public record FormaPagamento(
        Long id,
        String descricao,
        boolean aceitaValoresPreEstabelecidos,
        List<Double> listaValores,
        boolean ativo,
        LocalDateTime dataCriacao,
        LocalDateTime dataUltimaAtualizacao
) {
}
