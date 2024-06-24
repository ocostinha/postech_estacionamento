package com.fiap.postech.estacionamento.Service;


import com.fiap.postech.estacionamento.Entity.Usuario;
import com.fiap.postech.estacionamento.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario criarUsuario(Usuario usuario) {
        usuario.setDataCriacao(LocalDateTime.now());
        usuario.setDataAlteracao(LocalDateTime.now());
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> obterPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Optional<Usuario> login(String email, String senha) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isPresent() && usuario.get().getSenha().equals(senha)) {
            return usuario;
        }
        return Optional.empty();
    }

    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuario.setNomeCompleto(usuarioAtualizado.getNomeCompleto());
        usuario.setDocumento(usuarioAtualizado.getDocumento());
        usuario.setEmail(usuarioAtualizado.getEmail());
        usuario.setIdFormaPagamento(usuarioAtualizado.getIdFormaPagamento());
        usuario.setVeiculos(usuarioAtualizado.getVeiculos());
        usuario.setDataAlteracao(LocalDateTime.now());
        return usuarioRepository.save(usuario);
    }

    public Usuario trocarSenha(Long id, String novaSenha) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuario.setSenha(novaSenha);
        usuario.setDataAlteracao(LocalDateTime.now());
        return usuarioRepository.save(usuario);
    }

    public Usuario desativar(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuario.setAtivo(false);
        return usuarioRepository.save(usuario);
    }
}
