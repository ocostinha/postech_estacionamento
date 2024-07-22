package com.fiap.postech.estacionamento.resources.repository.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String fullName;
    private String document;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<VehicleEntity> vehicles;

    private String idPaymentMode;

    private LocalDateTime creationDate;
    private LocalDateTime updatedDate;

    private boolean active;
}