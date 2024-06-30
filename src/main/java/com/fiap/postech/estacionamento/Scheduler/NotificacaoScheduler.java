package com.fiap.postech.estacionamento.Scheduler;

import com.fiap.postech.estacionamento.Service.EmailService;
import com.fiap.postech.estacionamento.resources.repository.mongodb.EstacionamentoRepository;
import com.fiap.postech.estacionamento.resources.repository.mongodb.NotificacaoRepository;
import com.fiap.postech.estacionamento.resources.repository.entities.EstacionamentoEntity;
import com.fiap.postech.estacionamento.resources.repository.entities.NotificacaoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class NotificacaoScheduler {
    @Autowired
    private EstacionamentoRepository estacionamentoRepository;

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 * * * * *")
    public void sendNotifications() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneMinuteAgo = now.minusMinutes(1);

        List<EstacionamentoEntity> expiringEstacionamentos = estacionamentoRepository.findByDataLimiteSaidaBefore(oneMinuteAgo);
        for (EstacionamentoEntity estacionamento : expiringEstacionamentos) {
            emailService.sendEmail(estacionamento.getEmailCliente(), "Tempo de Estacionamento Esgotado", "Seu tempo de estacionamento acabou.");

            NotificacaoEntity notificacao = new NotificacaoEntity();
            notificacao.setRegistroEstacionamentoId(estacionamento.getId());
            notificacao.setEmailCliente(estacionamento.getEmailCliente());
            notificacao.setDataEnvio(now);

            notificacaoRepository.save(notificacao);
        }
    }
}

