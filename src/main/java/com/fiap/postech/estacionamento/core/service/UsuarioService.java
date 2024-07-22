package com.fiap.postech.estacionamento.core.service;

import com.fiap.postech.estacionamento.commoms.exception.NotFoundException;
import com.fiap.postech.estacionamento.commoms.exception.UnprocessableEntityException;
import com.fiap.postech.estacionamento.commoms.mappers.UserMapper;
import com.fiap.postech.estacionamento.core.domain.User;
import com.fiap.postech.estacionamento.resources.repository.UsuarioRepository;
import com.fiap.postech.estacionamento.resources.repository.entities.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private final UserMapper mapper;

    public User create(User user) {
        return mapper.toDomain(
                usuarioRepository.save(
                        mapper.toEntity(user)
                )
        );
    }

    public User getByEmail(String email) {
        return mapper.toDomain(
                usuarioRepository.findByEmailAndActive(email, true)
                        .orElseThrow(() -> new NotFoundException("Usuário não encontrado"))
        );
    }

    public User login(String email, String password) {
        return mapper.toDomain(
                usuarioRepository.findByEmailAndPasswordAndActive(email, password, true)
                        .orElseThrow(() -> new UnprocessableEntityException("Usuário ou senha inválido"))
        );
    }

    public User update(Long id, User user) {
        return mapper.toDomain(
                usuarioRepository.save(
                        mapper.update(user, usuarioRepository.findById(id)
                                .orElseThrow(() -> new UnprocessableEntityException("Usuário não encontrado")))
                )
        );
    }

    public User changePassword(Long id, String newPassword) {
        UserEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UnprocessableEntityException("Usuário não encontrado"));

        usuario.setPassword(newPassword);

        return mapper.toDomain(usuarioRepository.save(usuario));
    }

    @Cacheable(value = "getUserById", key = "#id")
    public User getUserById(Long id) {
        return mapper.toDomain(
                usuarioRepository.findByIdAndActive(id, true)
                        .orElseThrow(() -> new UnprocessableEntityException("Usuário não encontrado"))
        );
    }

    public User disable(Long id) {
        UserEntity userEntity = usuarioRepository.findByIdAndActive(id, true)
                .orElseThrow(() -> new UnprocessableEntityException("Usuário não encontrado"));

        userEntity.setActive(false);

        return mapper.toDomain(usuarioRepository.save(userEntity));
    }
}
