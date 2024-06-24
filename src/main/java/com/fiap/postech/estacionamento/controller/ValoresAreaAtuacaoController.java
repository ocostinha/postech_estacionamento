package com.fiap.postech.estacionamento.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.postech.estacionamento.dto.RequestCadastroValoresAreaDTO;
import com.fiap.postech.estacionamento.dto.ResponseValoresDTO;
import com.fiap.postech.estacionamento.entity.ValoresAreaAtuacao;
import com.fiap.postech.estacionamento.service.ValoresAreaAtuacaoService;

@RestController
@RequestMapping("/valores")
public class ValoresAreaAtuacaoController {

	@Autowired
	private ValoresAreaAtuacaoService service;
	// @Autowired
	// private service AreaAtuacao service area atuação

	@PostMapping
	public ResponseEntity<?> cadastrarValor(@RequestBody RequestCadastroValoresAreaDTO cadastroDto) {
//boolean areaAtiva = serviceArea.buscarAreaAtivaPassandooId;
//        if (!areaAtiva){
//            return  ResponseEntity.status(422).body(String.format("Não pode ser cadastrado um valor para area  não ativa"));
		// }
		try {
			ResponseValoresDTO response = service.cadastrarValor(cadastroDto);
			return ResponseEntity.status(201).body(response);
		} catch (Exception e) {
			return ResponseEntity.status(422)
					.body(String.format("Não foi possível realizar o cadastro do valor: %S ", e.getMessage()));
		}
	}

	@GetMapping("/consultarvalor/{id}")
	public ResponseEntity<ValoresAreaAtuacao> buscarValorPorId(@PathVariable Long id) {
		ValoresAreaAtuacao valorArea = service.consultarValorPorId(id);
		if (valorArea != null) {
			return ResponseEntity.status(200).body(valorArea);
		}
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/consultarporarea")
	public ResponseEntity<List<ValoresAreaAtuacao>> buscarValoresPorArea(@RequestParam Long areaAtuacaoId) {
		List<ValoresAreaAtuacao> valores = service.consultarValoresPorArea(areaAtuacaoId);
		if (valores.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.status(200).body(valores);
	}

	@PatchMapping("/atualizardatafim/{id}")
	public ResponseEntity<?> atualizarDataFimVigencia(@PathVariable Long id,
			@RequestParam LocalDateTime dataFimVigencia) {
		if (!dataFimVigencia.isAfter(LocalDateTime.now().plusDays(1))) {
			return ResponseEntity.status(400).build();

		}
		try {
			ValoresAreaAtuacao valorAtualizado = service.atualizarDataFimVigencia(id, dataFimVigencia);
			return ResponseEntity.status(202).body(valorAtualizado);
		} catch (Exception e) {
			return ResponseEntity.status(422)
					.body(String.format("Não foi possível realizar a atualização: %S", e.getMessage()));
		}
	}
}
