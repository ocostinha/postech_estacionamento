package com.fiap.postech.estacionamento.core.service;

import com.fiap.postech.estacionamento.commoms.exception.NotFoundException;
import com.fiap.postech.estacionamento.commoms.exception.UnprocessableEntityException;
import com.fiap.postech.estacionamento.commoms.mappers.UserMapper;
import com.fiap.postech.estacionamento.core.domain.User;
import com.fiap.postech.estacionamento.resources.repository.UserRepository;
import com.fiap.postech.estacionamento.resources.repository.entities.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository repository;

    @Autowired
    private final UserMapper mapper;

    public User create(User user) {
        return mapper.toDomain(
                repository.save(
                        mapper.toEntity(user)
                )
        );
    }

    public User getByEmail(String email) {
        return mapper.toDomain(
                repository.findByEmailAndActive(email, true)
                        .orElseThrow(() -> new NotFoundException("Usuário não encontrado"))
        );
    }

    public User login(String email, String password) {
        return mapper.toDomain(
                repository.findByEmailAndPasswordAndActive(email, password, true)
                        .orElseThrow(() -> new UnprocessableEntityException("Usuário ou senha inválido"))
        );
    }

    public User update(Long id, User user) {
        return mapper.toDomain(
                repository.save(
                        mapper.update(user, repository.findById(id)
                                .orElseThrow(() -> new UnprocessableEntityException("Usuário não encontrado")))
                )
        );
    }

    public User changePassword(Long id, String newPassword) {
        UserEntity usuario = repository.findById(id)
                .orElseThrow(() -> new UnprocessableEntityException("Usuário não encontrado"));

        usuario.setPassword(newPassword);

        return mapper.toDomain(repository.save(usuario));
    }

    @Cacheable(value = "getUserById", key = "#id")
    public User getUserById(Long id) {
        return mapper.toDomain(
                repository.findByIdAndActive(id, true)
                        .orElseThrow(() -> new UnprocessableEntityException("Usuário não encontrado"))
        );
    }

    public User disable(Long id) {
        UserEntity userEntity = repository.findByIdAndActive(id, true)
                .orElseThrow(() -> new UnprocessableEntityException("Usuário não encontrado"));

        userEntity.setActive(false);

        return mapper.toDomain(repository.save(userEntity));
    }
}
