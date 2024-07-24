package com.fiap.postech.estacionamento.resources.repository.entities;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Document
public class PaymentEntity {
	@MongoId
	private UUID id;
	private Long idUser;
	private Long idPaymentMode;
	private UUID idParking;
	private Double value;
	private int status;
	private LocalDateTime creationDate;
	private LocalDateTime updatedDate;
}
