package com.teceats.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order; // Pedido asociado

    @ManyToOne
    @JoinColumn(name = "dish_id", nullable = false)
    private Dish dish; // Platillo asociado

    @Column(nullable = false)
    private Integer quantity; // Cantidad del platillo

    @Column(nullable = false)
    private Double subtotal; // Subtotal (precio * cantidad)
}
