package com.fiap.postech.estacionamento.scheduler;

import com.fiap.postech.estacionamento.core.service.EstacionamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoScheduler {
    @Autowired
    private EstacionamentoService estacionamentoService;

    @Scheduled(cron = "0 * * * * *")
    public void sendNotifications() {
        estacionamentoService.notifyExpirados();
    }

    @Scheduled(cron = "0 * * * * *")
    public void sendExpirationAlerts() {
        estacionamentoService.alertExpiracao();
    }
}

