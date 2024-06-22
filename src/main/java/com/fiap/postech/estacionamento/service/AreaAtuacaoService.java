package com.fiap.postech.estacionamento.service;

import com.fiap.postech.estacionamento.controller.exception.ControllerNotFoundException;
import com.fiap.postech.estacionamento.controller.exception.UnprocessableEntityException;
import com.fiap.postech.estacionamento.dto.AreaAtuacaoDTO;
import com.fiap.postech.estacionamento.entities.AreaAtuacao;
import com.fiap.postech.estacionamento.repository.AreaAtuacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AreaAtuacaoService {

    private final AreaAtuacaoRepository areaAtuacaoRepository;

    public AreaAtuacaoService(AreaAtuacaoRepository areaAtuacaoRepository) {
        this.areaAtuacaoRepository = areaAtuacaoRepository;
    }

    public List<AreaAtuacaoDTO> findAll() {
        List<AreaAtuacao> areasAtuacoes = areaAtuacaoRepository.findAll();
        if (areasAtuacoes.isEmpty()) {
            throw new ControllerNotFoundException("Nenhuma área de atuação encontrada");
        }
        return areasAtuacoes.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public AreaAtuacaoDTO findById(Long id) {
        AreaAtuacao areaAtuacao = areaAtuacaoRepository.findById(id)
                    .orElseThrow(() ->
                            new ControllerNotFoundException("Área de atuação não encontrada"));

        return toDTO(areaAtuacao);
    }

    public AreaAtuacaoDTO save(AreaAtuacaoDTO areaAtuacaoDTO) {
        AreaAtuacao areaAtuacao = toEntity(areaAtuacaoDTO);
        areaAtuacao.setAtivo(true);
        areaAtuacao.setDataCriacao(LocalDateTime.now());
        areaAtuacao.setDataUltimaModificacao(LocalDateTime.now());
        areaAtuacao = areaAtuacaoRepository.save(areaAtuacao);
        return toDTO(areaAtuacao);
    }

    public AreaAtuacaoDTO update(Long id, String nomeArea) {
        try {
            AreaAtuacao areaAtuacao = areaAtuacaoRepository.getReferenceById(id);
            areaAtuacao.setDataUltimaModificacao(LocalDateTime.now());
            areaAtuacao.setNomeArea(nomeArea);

            areaAtuacao = areaAtuacaoRepository.save(areaAtuacao);
            return toDTO(areaAtuacao);
        }catch(EntityNotFoundException e) {
            throw new UnprocessableEntityException("Área de atuação não encontrada");
        }
    }

    public AreaAtuacaoDTO desativar(Long id) {
        try {
            AreaAtuacao areaAtuacao = areaAtuacaoRepository.getReferenceById(id);
            areaAtuacao.setAtivo(false);

            areaAtuacao = areaAtuacaoRepository.save(areaAtuacao);
            return toDTO(areaAtuacao);
        }catch(EntityNotFoundException e) {
            throw new UnprocessableEntityException("Área de atuação não encontrada");
        }
    }

    private AreaAtuacaoDTO toDTO(AreaAtuacao areaAtuacao) {
      return new AreaAtuacaoDTO(
              areaAtuacao.getId(),
              areaAtuacao.getNomeArea(),
              areaAtuacao.getCidade(),
              areaAtuacao.getEstado(),
              areaAtuacao.getAtivo(),
              areaAtuacao.getDataCriacao(),
              areaAtuacao.getDataUltimaModificacao()
      );
    }

    private AreaAtuacao toEntity(AreaAtuacaoDTO areaAtuacaoDTO) {
        return new AreaAtuacao(
                areaAtuacaoDTO.id(),
                areaAtuacaoDTO.nome(),
                areaAtuacaoDTO.cidade(),
                areaAtuacaoDTO.estado(),
                areaAtuacaoDTO.ativo(),
                areaAtuacaoDTO.dataCriacao(),
                areaAtuacaoDTO.dataUltimaModificacao()
        );
    }
}
