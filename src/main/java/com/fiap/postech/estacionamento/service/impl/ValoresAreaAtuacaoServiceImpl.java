package com.fiap.postech.estacionamento.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.postech.estacionamento.dto.RequestCadastroValoresAreaDTO;
import com.fiap.postech.estacionamento.dto.ResponseValoresDTO;
import com.fiap.postech.estacionamento.entity.ValoresAreaAtuacao;
import com.fiap.postech.estacionamento.repository.ValoresAreaAtuacaoRepository;
import com.fiap.postech.estacionamento.service.ValoresAreaAtuacaoService;

@Service
public class ValoresAreaAtuacaoServiceImpl implements ValoresAreaAtuacaoService {

	@Autowired
	private ValoresAreaAtuacaoRepository repository;

	@Override
	public ResponseValoresDTO cadastrarValor(RequestCadastroValoresAreaDTO cadastroDto) {
		ValoresAreaAtuacao valorExistente = repository
				.findFirstByAreaAtuacaoIdAndDataFimVigenciaIsNull(cadastroDto.getAreaAtuacaoId());
		if (valorExistente != null) {
			valorExistente.setDataFimVigencia(cadastroDto.getDataInicioVigencia().minusDays(1));
			repository.save(valorExistente);
		}

		ValoresAreaAtuacao novoValor = new ValoresAreaAtuacao();
		novoValor.setIdAreaAtuacao(cadastroDto.getAreaAtuacaoId());
		novoValor.setValorPorHora(cadastroDto.getValorPorHora());
		novoValor.setDataInicioVigencia(cadastroDto.getDataInicioVigencia());
		novoValor.setDataCriacao(LocalDateTime.now());
		novoValor.setDataAlteracao(LocalDateTime.now());

		repository.save(novoValor);

		ResponseValoresDTO response = new ResponseValoresDTO();
		response.setId(novoValor.getId());
		response.setDataCriacao(novoValor.getDataCriacao());
		response.setDataAlteracao(novoValor.getDataAlteracao());

		return response;
	}

	@Override
	public ValoresAreaAtuacao consultarValorPorId(Long id) {
		Optional<ValoresAreaAtuacao> valor = repository.findById(id);
		return valor.orElse(null);
	}

	@Override
	public List<ValoresAreaAtuacao> consultarValoresPorArea(Long areaAtuacaoId) {
		return repository.findByAreaAtuacaoId(areaAtuacaoId);
	}

	@Override
	public ValoresAreaAtuacao atualizarDataFimVigencia(Long id, LocalDateTime dataFimVigencia) {
		Optional<ValoresAreaAtuacao> valorRetornado = repository.findById(id);
		if (!valorRetornado.isPresent()) {
			throw new IllegalArgumentException("Valor não encontrado");
		}

		ValoresAreaAtuacao valorAtualizado = valorRetornado.get();
		valorAtualizado.setDataFimVigencia(dataFimVigencia);
		valorAtualizado.setDataAlteracao(LocalDateTime.now());

		repository.save(valorAtualizado);

		return valorAtualizado;
	}
}
