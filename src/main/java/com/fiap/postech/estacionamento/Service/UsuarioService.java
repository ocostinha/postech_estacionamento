package com.fiap.postech.estacionamento.Service;


import com.fiap.postech.estacionamento.commoms.exception.NotFoundException;
import com.fiap.postech.estacionamento.commoms.exception.UnauthorizedException;
import com.fiap.postech.estacionamento.commoms.mappers.UsuarioMapper;
import com.fiap.postech.estacionamento.core.domain.Usuario;
import com.fiap.postech.estacionamento.resources.repository.entities.UsuarioEntity;
import com.fiap.postech.estacionamento.resources.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private final UsuarioMapper mapper;

    public Usuario criarUsuario(Usuario usuario) {
        return mapper.toDomain(
                usuarioRepository.save(
                        mapper.toEntity(usuario)
                )
        );
    }

    public Usuario obterPorEmail(String email) {
        return mapper.toDomain(
                usuarioRepository.findByEmailAndAtivo(email, true)
                        .orElseThrow(() -> new NotFoundException("Usuário não encontrado"))
        );
    }

    public Usuario login(String email, String senha) {
        return mapper.toDomain(
                usuarioRepository.findByEmailAndSenhaAndAtivo(email, senha, true)
                        .orElseThrow(() -> new UnauthorizedException("Usuário ou senha inválido"))
        );
    }

    public Usuario atualizarUsuario(Long id, Usuario usuario) {
        return mapper.toDomain(
                usuarioRepository.save(
                        mapper.update(usuario, usuarioRepository.findById(id)
                                .orElseThrow(() -> new UnauthorizedException("Usuário não encontrado")))
                )
        );
    }

    public Usuario trocarSenha(Long id, String novaSenha) {
        UsuarioEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UnauthorizedException("Usuário não encontrado"));

        usuario.setSenha(novaSenha);

        return mapper.toDomain(usuarioRepository.save(usuario));
    }

    public Usuario desativar(Long id) {
        UsuarioEntity usuarioEntity = usuarioRepository.findById(id)
                .orElseThrow(() -> new UnauthorizedException("Usuário não encontrado"));

        usuarioEntity.setAtivo(false);

        return mapper.toDomain(usuarioRepository.save(usuarioEntity));
    }
}
