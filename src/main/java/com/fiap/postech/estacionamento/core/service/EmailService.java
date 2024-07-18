package com.fiap.postech.estacionamento.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public String sendEmail(String destinatario, String assunto, String mensagem) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("nicholasbarbosads2001@gmail.com");
            message.setTo(destinatario);
            message.setSubject(assunto);
            message.setText(mensagem);

            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            return "Erro ao enviar email! "  + e.getLocalizedMessage();
        }
    }
}
