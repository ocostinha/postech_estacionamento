package com.fiap.postech.estacionamento.core.service;

import com.fiap.postech.estacionamento.commoms.mappers.PaymentMapper;
import com.fiap.postech.estacionamento.resources.repository.mongodb.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PaymentMapper mapper;

    void createPayment(Long idUser, Long idPaymentMode, UUID idParking) {
        paymentRepository.save(
                mapper.build(
                        idUser, idPaymentMode, idParking
                )
        );
    }

    void liquidatePayment(UUID idParking, Integer parkedHours, Double hourValue, String sendReceiptTo) {
        paymentRepository.findByIdParking(idParking).ifPresent(payment -> {
            Double totalValue = calcValue(hourValue, parkedHours);
            payment.setValue(totalValue);
            payment.setStatus(1);

            paymentRepository.save(payment);

            emailService.sendEmail(
                    sendReceiptTo,
                    "Recibo de estacionamento",
                    "Segue recibo de estacionamento referente ao ticket " + idParking +
                            "\n. Tempo estacionado: " + parkedHours + "," +
                            "\n. Tarifa por hora: " + hourValue + "," +
                            "\n. Valor total: R$" + totalValue + ".");
        });
    }

    private Double calcValue(Double value, Integer hour) {
        return value * hour;
    }
}
