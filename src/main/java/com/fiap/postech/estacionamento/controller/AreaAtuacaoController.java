package com.fiap.postech.estacionamento.controller;

import com.fiap.postech.estacionamento.dto.AreaAtuacaoDTO;
import com.fiap.postech.estacionamento.service.AreaAtuacaoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.geom.Area;
import java.util.List;


@RestController
@RequestMapping("/areaAtuacao")
public class AreaAtuacaoController {
    private final AreaAtuacaoService areaAtuacaoService;

    public AreaAtuacaoController(AreaAtuacaoService areaAtuacaoService) {
        this.areaAtuacaoService = areaAtuacaoService;
    }

    @GetMapping
    public ResponseEntity<List<AreaAtuacaoDTO>> findAll() {
        List<AreaAtuacaoDTO> areasDTOS = areaAtuacaoService.findAll();
        return ResponseEntity.ok(areasDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AreaAtuacaoDTO> findById(@PathVariable Long id) {
        AreaAtuacaoDTO areaAtuacaoDTO = areaAtuacaoService.findById(id);
        return ResponseEntity.ok(areaAtuacaoDTO);
    }

    @PostMapping
    public ResponseEntity<AreaAtuacaoDTO> save(@RequestBody AreaAtuacaoDTO areaAtuacaoDTO) {
        AreaAtuacaoDTO savedAreaAtuacaoDTO = areaAtuacaoService.save(areaAtuacaoDTO);
        return new ResponseEntity<>(savedAreaAtuacaoDTO, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AreaAtuacaoDTO> update(@PathVariable Long id, @RequestParam String nomeArea) {
       AreaAtuacaoDTO updatedAreaAtuacaoDTO = areaAtuacaoService.update(id, nomeArea);
       return ResponseEntity.accepted().body(updatedAreaAtuacaoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AreaAtuacaoDTO> delete(@PathVariable Long id) {
        AreaAtuacaoDTO desactivateAreaAtuacaoDTO = areaAtuacaoService.desativar(id);
        return ResponseEntity.accepted().body(desactivateAreaAtuacaoDTO);
    }
}
