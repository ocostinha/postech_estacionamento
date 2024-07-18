package com.fiap.postech.estacionamento.core.domain;

import java.time.LocalDateTime;

public record Estacionamento(
        Long id,
        String placa,
        Long idAreaAtuacao,
        Long idFormaPagamento,
        LocalDateTime inicioEstacionamento,
        LocalDateTime finalEstacionamento,
        LocalDateTime dataCriacao,
        LocalDateTime dataUltimaModificacao
) {

}

