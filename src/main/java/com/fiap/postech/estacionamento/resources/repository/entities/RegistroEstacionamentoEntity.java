package com.fiap.postech.estacionamento.resources.repository.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Data
@Document(collection = "estacionamentos")
public class RegistroEstacionamentoEntity {
    @Id
    private String id;
    private String idUsuario;
    private String placaVeiculo;
    private String idAreaEstacionamento;
    private LocalDateTime dataInicioEstacionamento;
    private LocalDateTime dataFinalEstacionamento;
    private String idFormaPagamento;

}
