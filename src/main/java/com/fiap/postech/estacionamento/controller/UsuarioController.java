package com.fiap.postech.estacionamento.controller;


import com.fiap.postech.estacionamento.commoms.mappers.UsuarioMapper;
import com.fiap.postech.estacionamento.controller.dto.UsuarioAtualizacaoRequestDTO;
import com.fiap.postech.estacionamento.controller.dto.UsuarioMinimalResponseDTO;
import com.fiap.postech.estacionamento.controller.dto.UsuarioRequestDTO;
import com.fiap.postech.estacionamento.controller.dto.UsuarioResponseDTO;
import com.fiap.postech.estacionamento.core.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {

    @Autowired
    private final UsuarioService usuarioService;

    @Autowired
    private final UsuarioMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponseDTO criarUsuario(@Valid @RequestBody UsuarioRequestDTO request) {
        return mapper.toResponse(
                usuarioService.criarUsuario(
                        mapper.toDomain(request)
                )
        );
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponseDTO obterPorEmail(@RequestParam String email) {
        return mapper.toResponse(usuarioService.obterPorEmail(email));
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponseDTO login(@RequestParam String email, @RequestParam String senha) {
        return mapper.toResponse(usuarioService.login(email, senha));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UsuarioResponseDTO atualizarUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioAtualizacaoRequestDTO request) {
        return mapper.toResponse(
                    usuarioService.atualizarUsuario(id, mapper.toDomain(request)
                )
        );
    }

    @PatchMapping("/{id}/senha")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UsuarioResponseDTO trocarSenha(@PathVariable Long id, @RequestParam String novaSenha) {
        return mapper.toResponse(usuarioService.trocarSenha(id, novaSenha));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioMinimalResponseDTO getUserById(@PathVariable Long id) {
        return mapper.toMinimalResponse(usuarioService.getUserById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UsuarioResponseDTO desativar(@PathVariable Long id) {
        return mapper.toResponse(usuarioService.desativar(id));
    }
}
