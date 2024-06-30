package com.fiap.postech.estacionamento.resources.repository.entities;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Document(collection = "estacionamento")
public class EstacionamentoEntity {
    @Id
    private String id;
    private String emailCliente;
    private LocalDateTime dataLimiteSaida;
}
