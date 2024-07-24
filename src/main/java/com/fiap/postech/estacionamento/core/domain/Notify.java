package com.fiap.postech.estacionamento.core.domain;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Notify {
    private String id;
    private UUID idParking;
    private String email;
    private LocalDateTime sentDate;
}
