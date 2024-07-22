package com.fiap.postech.estacionamento.core.domain;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class User {
    private Long id;
    private String fullName;
    private String document;
    private String email;
    private String password;
    private List<Vehicle> vehicles;
    private String idPaymentMode;
    private LocalDateTime creationDate;
    private LocalDateTime updatedDate;
    private boolean active;
}