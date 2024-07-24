package com.fiap.postech.estacionamento.scheduler;

import com.fiap.postech.estacionamento.core.service.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NotifyScheduler {
    @Autowired
    private NotifyService service;

    @Scheduled(cron = "0 * * * * *")
    public void sendExpirationAlert() {
        service.alertExpiration();
    }

    @Scheduled(cron = "0 * * * * *")
    public void sendFutureExpirationAlerts() {
        service.alertFutureExpiration();
    }
}

