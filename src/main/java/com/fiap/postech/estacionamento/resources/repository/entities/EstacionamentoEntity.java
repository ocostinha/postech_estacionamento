package com.fiap.postech.estacionamento.resources.repository.entities;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Document(collection = "estacionamentos")
public class EstacionamentoEntity {
    @MongoId
    private UUID id;
    private Long idUsuario;
    private String placaVeiculo;
    private String idAreaEstacionamento;
    private LocalDateTime dataInicioEstacionamento;
    private LocalDateTime dataFinalEstacionamento;
    private Long idFormaPagamento;
    private Boolean finalizado;
}
