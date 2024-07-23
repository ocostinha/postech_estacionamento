package com.fiap.postech.estacionamento.resources.repository.entities;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Document(collection = "notificacoes")
public class NotificacaoEntity {
    @MongoId
    private UUID id;
    private UUID idParking;
    private String email;
    private LocalDateTime dataEnvio;
}
