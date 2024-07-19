package com.fiap.postech.estacionamento.scheduler;

import com.fiap.postech.estacionamento.core.service.NotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoScheduler {
    @Autowired
    private NotificacaoService service;

    @Scheduled(cron = "0 * * * * *")
    public void sendExpirationAlert() {
        service.alertExpiration();
    }

    @Scheduled(cron = "0 * * * * *")
    public void sendFutureExpirationAlerts() {
        service.alertFutureExpiration();
    }
}

