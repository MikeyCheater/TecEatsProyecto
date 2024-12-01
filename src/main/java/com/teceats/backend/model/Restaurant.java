package com.teceats.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ownerEmail;
    private String name;
    private boolean enabled;
}
