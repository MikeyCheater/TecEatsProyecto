package com.teceats.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "approvals")
public class Approval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin; // Administrador que aprueba/rechaza

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant; // Restaurante aprobado/rechazado

    @Column(nullable = false)
    private LocalDateTime approvalTime; // Fecha y hora de la aprobación

    @Column(nullable = true)
    private String comments; // Comentarios del administrador
}
