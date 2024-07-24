package com.fiap.postech.estacionamento.core.service;

import com.fiap.postech.estacionamento.commoms.mappers.NotifyMapper;
import com.fiap.postech.estacionamento.resources.repository.mongodb.NotifyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
public class NotifyService {

    @Autowired
    private NotifyRepository repository;

    @Autowired
    private NotifyMapper mapper;

    @Autowired
    private ParkingService parkingService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Value("${id.payment.pix:1}")
    private Long idPagamentoPix;

    private void create(UUID idParking, String email) {
        repository.save(
                mapper.build(idParking, email)
        );
    }

    public void alertExpiration() {
        parkingService
                .getExpired(LocalDateTime.now().withSecond(59).minusMinutes(1))
                .forEach(estacionamento -> {
                    String email = userService.getUserById(estacionamento.getIdUser()).getEmail();
                    String subject;
                    String message;

                    if (Objects.equals(estacionamento.getIdPaymentMode(), idPagamentoPix)) {
                        subject = "Tempo de Estacionamento Esgotado";
                        message = "Seu tempo de estacionamento acabou. Por favor, retire seu veículo";
                    } else {
                        subject = "Tempo de Estacionamento Renovado";
                        message = "Seu tempo de estacionamento foi renovado em mais uma hora.";

                        parkingService.addOneHourLimitPark(estacionamento.getId());
                    }

                    emailService.sendEmail(
                            email,
                            subject,
                            message
                    );

                    create(estacionamento.getId(), email);
                });
    }

    public void alertFutureExpiration() {
        parkingService.getFutureExpiration(
                LocalDateTime.now().minusMinutes(50).withSecond(0),
                LocalDateTime.now().minusMinutes(50).withSecond(59)
        ).forEach(estacionamento -> {
            String email = userService.getUserById(estacionamento.getIdUser()).getEmail();

            if (Objects.equals(estacionamento.getIdPaymentMode(), idPagamentoPix)) {
                emailService.sendEmail(email,
                        "Aviso de Expiração de Estacionamento",
                        "Sua hora de estacionamento está prestes a expirar, programe-se para retirar seu veículo!"
                );
            } else {
                emailService.sendEmail(email,
                        "Aviso de Expiração de Estacionamento",
                        "Sua hora de estacionamento está prestes a expirar, " +
                                "ela termirá em 10 minutos e será renovado automáticamente por mais uma hora!"
                );
            }

            create(estacionamento.getId(), email);
        });
    }
}
