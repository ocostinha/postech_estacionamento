package com.fiap.postech.estacionamento.core.service;

import com.fiap.postech.estacionamento.commoms.exception.NotFoundException;
import com.fiap.postech.estacionamento.commoms.exception.UnprocessableEntityException;
import com.fiap.postech.estacionamento.commoms.mappers.AreaAtuacaoMapper;
import com.fiap.postech.estacionamento.core.domain.AreaAtuacao;
import com.fiap.postech.estacionamento.resources.repository.AreaAtuacaoRepository;
import com.fiap.postech.estacionamento.resources.repository.entities.AreaAtuacaoEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AreaAtuacaoService {

    @Autowired
    private final AreaAtuacaoRepository areaAtuacaoRepository;

    @Autowired
    private final AreaAtuacaoMapper mapper;

    public List<AreaAtuacao> findAll() {
        List<AreaAtuacaoEntity> areasAtuacoes = areaAtuacaoRepository.findByActive(true);

        if (areasAtuacoes.isEmpty()) {
            throw new NotFoundException("Nenhuma área de atuação encontrada");
        }

        return areasAtuacoes.stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    public AreaAtuacao findById(Long id) {
        return mapper.toDomain(
                areaAtuacaoRepository.findByIdAndActive(id, true)
                        .orElseThrow(() ->
                                new NotFoundException("Área de atuação não encontrada")));
    }

    public void validAreaAtuacao(Long id) {
        areaAtuacaoRepository.findByIdAndActive(id, true)
                .orElseThrow(() ->
                        new NotFoundException("Área de atuação não encontrada"));
    }

    public AreaAtuacao save(AreaAtuacao areaAtuacao) {
        return mapper.toDomain(
                areaAtuacaoRepository.save(
                        mapper.toEntity(areaAtuacao)
                )
        );
    }

    public AreaAtuacao update(Long id, String nomeArea) {
        try {
            return mapper.toDomain(
                    areaAtuacaoRepository.save(
                            mapper.update(nomeArea, areaAtuacaoRepository.getReferenceById(id))
                    )
            );
        } catch (EntityNotFoundException e) {
            throw new UnprocessableEntityException("Área de atuação não encontrada");
        }
    }

    public AreaAtuacao desativar(Long id) {
        try {
            AreaAtuacaoEntity areaAtuacaoEntity = areaAtuacaoRepository.getReferenceById(id);
            areaAtuacaoEntity.setActive(false);

            return mapper.toDomain(
                    areaAtuacaoRepository.save(
                            areaAtuacaoEntity
                    )
            );
        } catch (EntityNotFoundException e) {
            throw new UnprocessableEntityException("Área de atuação não encontrada");
        }
    }
}
