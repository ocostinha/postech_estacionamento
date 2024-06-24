package com.fiap.postech.estacionamento.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fiap.postech.estacionamento.dto.RequestCadastroValoresAreaDTO;
import com.fiap.postech.estacionamento.dto.ResponseValoresDTO;
import com.fiap.postech.estacionamento.entity.ValoresAreaAtuacao;

@Service
public interface ValoresAreaAtuacaoService {

	ResponseValoresDTO cadastrarValor(RequestCadastroValoresAreaDTO cadastroDto);

	ValoresAreaAtuacao consultarValorPorId(Long id);

	List<ValoresAreaAtuacao> consultarValoresPorArea(Long areaAtuacaoId);

	ValoresAreaAtuacao atualizarDataFimVigencia(Long id, LocalDateTime dataFimVigencia);
}
