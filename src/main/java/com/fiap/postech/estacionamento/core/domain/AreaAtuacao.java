package com.fiap.postech.estacionamento.core.domain;

import java.time.LocalDateTime;

public record AreaAtuacao(
        Long id,
        String nome,
        String cidade,
        String estado,
        boolean ativo,
        LocalDateTime dataCriacao,
        LocalDateTime dataUltimaModificacao
) {

}

