package com.fiap.postech.estacionamento.resources.repository.entities;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Document(collection = "notificacoes")
public class NotificacaoEntity {
    @Id
    private String id;
    private String registroEstacionamentoId;
    private String emailCliente;
    private LocalDateTime dataEnvio;
}
