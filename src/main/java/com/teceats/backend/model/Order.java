package com.teceats.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Usuario que realiza el pedido

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant; // Restaurante del pedido

    @Column(nullable = false)
    private LocalDateTime orderTime; // Fecha y hora del pedido

    @Column(nullable = false)
    private String deliveryLocation; // Ubicación de entrega dentro de Tecsup

    @Column(nullable = false)
    private String status; // Estado del pedido: pendiente, en proceso, entregado

    @Column(nullable = false)
    private Double total; // Total del pedido
}
