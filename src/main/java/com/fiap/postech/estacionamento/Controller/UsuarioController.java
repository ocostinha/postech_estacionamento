package com.fiap.postech.estacionamento.Controller;


import com.fiap.postech.estacionamento.Entity.Usuario;
import com.fiap.postech.estacionamento.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.criarUsuario(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Usuario> obterPorEmail(@RequestParam String email) {
        Optional<Usuario> usuario = usuarioService.obterPorEmail(email);
        if (usuario.isPresent()) {
            Usuario usuarioSemSenha = usuario.get();
            usuarioSemSenha.setSenha(null);
            return new ResponseEntity<>(usuarioSemSenha, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestParam String email, @RequestParam String senha) {
        Optional<Usuario> usuario = usuarioService.login(email, senha);
        if (usuario.isPresent()) {
            Usuario usuarioSemSenha = usuario.get();
            usuarioSemSenha.setSenha(null);
            return new ResponseEntity<>(usuarioSemSenha, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        try {
            Usuario usuario = usuarioService.atualizarUsuario(id, usuarioAtualizado);
            usuario.setSenha(null);
            return new ResponseEntity<>(usuario, HttpStatus.ACCEPTED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PatchMapping("/{id}/senha")
    public ResponseEntity<Usuario> trocarSenha(@PathVariable Long id, @RequestParam String novaSenha) {
        try {
            Usuario usuario = usuarioService.trocarSenha(id, novaSenha);
            usuario.setSenha(null);
            return new ResponseEntity<>(usuario, HttpStatus.ACCEPTED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> desativar(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.desativar(id);
            usuario.setSenha(null);
            return new ResponseEntity<>(usuario, HttpStatus.ACCEPTED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}

