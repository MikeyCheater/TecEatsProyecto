package com.teceats.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String picture;

    @Enumerated(EnumType.STRING)
    private Role role;  // Enum de roles para diferenciarlos entre admin, usuario, etc.

    public enum Role {
        USER,
        ADMIN,
        RESTAURANT_OWNER
    }
}
