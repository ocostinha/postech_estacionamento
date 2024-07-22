package com.fiap.postech.estacionamento.core.service;

import com.fiap.postech.estacionamento.commoms.mappers.NotificacaoMapper;
import com.fiap.postech.estacionamento.core.domain.Notificacao;
import com.fiap.postech.estacionamento.resources.repository.mongodb.NotificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository repository;

    @Autowired
    private NotificacaoMapper mapper;

    @Autowired
    private EstacionamentoService estacionamentoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmailService emailService;

    @Value("${id.pagamento.pix:1}")
    private Long idPagamentoPix;

    private Notificacao create(UUID idEstacionamento, String email) {
        return mapper.toDomain(
                repository.save(
                        mapper.build(idEstacionamento, email)
                )
        );
    }

    public void alertExpiration() {
        estacionamentoService
                .getExpirados(LocalDateTime.now().withSecond(59).minusMinutes(1))
                .forEach(estacionamento -> {
                    String email = usuarioService.getUserById(estacionamento.getIdUsuario()).getEmail();
                    String subject;
                    String message;

                    if (Objects.equals(estacionamento.getIdPaymentMode(), idPagamentoPix)) {
                        subject = "Tempo de Estacionamento Esgotado";
                        message = "Seu tempo de estacionamento acabou.";
                    } else {
                        subject = "Tempo de Estacionamento Renovado";
                        message = "Seu tempo de estacionamento foi renovado em mais uma hora.";
                        estacionamentoService.addOneHourLimitPark(estacionamento.getId());
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
        estacionamentoService.getFutureExpiration(
                LocalDateTime.now().minusMinutes(50).withSecond(0),
                LocalDateTime.now().minusMinutes(50).withSecond(59)
        ).forEach(estacionamento -> {
            String email = usuarioService.getUserById(estacionamento.getIdUsuario()).getEmail();

            emailService.sendEmail(email,
                    "Aviso de Expiração de Estacionamento",
                    "Sua hora de estacionamento está prestes a expirar, " +
                            "ela termirá em 10 minutos e será renovado automáticamente por mais uma hora!"
            );

            create(estacionamento.getId(), email);
        });
    }
}
