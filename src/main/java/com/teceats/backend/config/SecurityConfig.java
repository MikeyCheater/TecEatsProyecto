package com.teceats.backend.config;

import com.teceats.backend.service.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService) {
        this.customOAuth2UserService = customOAuth2UserService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)  // Deshabilitar CSRF correctamente
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/", "/login", "/error").permitAll()  // Permitir acceso público a estas rutas
                        .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")  // Solo los administradores pueden acceder a /admin/**
                        .requestMatchers("/restaurant/**").hasAuthority("ROLE_RESTAURANTE")  // Solo los propietarios de restaurantes pueden acceder a /restaurant/**
                        .requestMatchers("/user/**").hasAuthority("ROLE_USER")  // Solo los usuarios comunes pueden acceder a /user/**
                        .anyRequest().authenticated()  // Cualquier otra solicitud debe estar autenticada
                )
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService))  // Configurar el servicio personalizado para OAuth2
                );

        return http.build();
    }
}
