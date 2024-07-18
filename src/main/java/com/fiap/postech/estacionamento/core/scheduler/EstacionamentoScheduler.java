package com.fiap.postech.estacionamento.core.scheduler;

import com.fiap.postech.estacionamento.core.service.EmailService;
import com.fiap.postech.estacionamento.resources.repository.EstacionamentoRegistroRepository;
import com.fiap.postech.estacionamento.resources.repository.NotificacaoEmailRepository;
import com.fiap.postech.estacionamento.resources.repository.entities.EstacionamentoRegistroEntity;
import com.fiap.postech.estacionamento.resources.repository.entities.NotificacaoEmailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;

import java.time.LocalDateTime;

@Service
public class EstacionamentoScheduler {

    @Autowired
    private EstacionamentoRegistroRepository estacionamentoRegistroRepository;

    @Autowired
    private NotificacaoEmailRepository notificacaoEmailRepository;

    @Autowired
    private EmailService emailService;

    @Scheduled(fixedDelay = 60000)
    public void notificar() {
        LocalDateTime umaHoraAtras = LocalDateTime.now().minusHours(1);
        List<EstacionamentoRegistroEntity> registros = estacionamentoRegistroRepository.findByHoraFimIsNullAndHoraInicioLessThanEqual(umaHoraAtras);
        for (EstacionamentoRegistroEntity registro : registros) {
            String email = registro.getEmail();
            String assunto = "Tempo de estacionamento incrementado";
            String textoCorpo = "Seu tempo de estacionamento foi incrementado em mais uma hora";
            emailService.sendEmail(email, assunto, textoCorpo);

            NotificacaoEmailEntity notificacaoEmail = new NotificacaoEmailEntity();
            notificacaoEmail.setRegistroEstacionamentoID(registro.getId());
            notificacaoEmail.setEmail(email);
            notificacaoEmail.setHoraEnviada(LocalDateTime.now());
            notificacaoEmailRepository.save(notificacaoEmail);

            registro.setHoraInicio(registro.getHoraInicio().plusHours(1));
            estacionamentoRegistroRepository.save(registro);
            System.out.println("Cheguei aqui!");
        }
    }
}
