package com.teceats.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "dishes")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // Nombre del platillo

    @Column(nullable = false)
    private String description; // Descripción del platillo

    @Column(nullable = false)
    private Double price; // Precio del platillo

    @Column(nullable = false)
    private String image; // URL de la imagen del platillo

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant; // Relación con el restaurante
}
