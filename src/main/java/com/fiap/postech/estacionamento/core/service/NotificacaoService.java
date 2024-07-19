package com.fiap.postech.estacionamento.core.service;

import com.fiap.postech.estacionamento.commoms.mappers.NotificacaoMapper;
import com.fiap.postech.estacionamento.core.domain.Notificacao;
import com.fiap.postech.estacionamento.resources.repository.mongodb.NotificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    @Autowired
    private NotificacaoMapper mapper;

    Notificacao create(UUID idEstacionamento, String email) {
        return mapper.toDomain(
                notificacaoRepository.save(
                        mapper.build(idEstacionamento, email)
                )
        );
    }
}
