package com.fiap.postech.estacionamento.resources.repository.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "registroEstacionamento")
@Data
public class EstacionamentoRegistroEntity {
    @Id
    private String id;
    private String usuarioId;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFim;
    private String email;
}
