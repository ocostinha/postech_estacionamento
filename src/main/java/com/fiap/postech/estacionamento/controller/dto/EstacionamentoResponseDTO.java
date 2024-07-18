package com.fiap.postech.estacionamento.controller.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class EstacionamentoResponseDTO {
    private Long id;
    private String placa;
    private Long idAreaAtuacao;
    private Long idFormaPagamento;
    private LocalDateTime inicioEstacionamento;
    private LocalDateTime finalEstacionamento;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataUltimaModificacao;
}
