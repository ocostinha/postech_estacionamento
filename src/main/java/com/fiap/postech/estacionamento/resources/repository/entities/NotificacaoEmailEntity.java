package com.fiap.postech.estacionamento.resources.repository.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "notificacoesEmail")
@Data
public class NotificacaoEmailEntity {
    @Id
    private String id;
    private String registroEstacionamentoID;
    private String email;
    private LocalDateTime horaEnviada;
}
