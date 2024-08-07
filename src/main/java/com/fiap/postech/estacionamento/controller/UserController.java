package com.fiap.postech.estacionamento.controller;


import com.fiap.postech.estacionamento.commoms.mappers.UserMapper;
import com.fiap.postech.estacionamento.controller.dto.MinimalUserResponseDTO;
import com.fiap.postech.estacionamento.controller.dto.UpdateUserRequestDTO;
import com.fiap.postech.estacionamento.controller.dto.UserRequestDTO;
import com.fiap.postech.estacionamento.controller.dto.UserResponseDTO;
import com.fiap.postech.estacionamento.core.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final UserMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO createUser(@Valid @RequestBody UserRequestDTO request) {
        return mapper.toResponse(
                userService.create(
                        mapper.toDomain(request)
                )
        );
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDTO getByEmail(@RequestParam String email) {
        return mapper.toResponse(userService.getByEmail(email));
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDTO login(@RequestParam String email, @RequestParam String password) {
        return mapper.toResponse(userService.login(email, password));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserResponseDTO update(@PathVariable Long id, @Valid @RequestBody UpdateUserRequestDTO request) {
        return mapper.toResponse(
                    userService.update(id, mapper.toDomain(request)
                )
        );
    }

    @PatchMapping("/{id}/password")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserResponseDTO changePassword(@PathVariable Long id, @RequestParam String newPassword) {
        return mapper.toResponse(userService.changePassword(id, newPassword));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MinimalUserResponseDTO getUserById(@PathVariable Long id) {
        return mapper.toMinimalResponse(userService.getUserById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserResponseDTO disable(@PathVariable Long id) {
        return mapper.toResponse(userService.disable(id));
    }
}
