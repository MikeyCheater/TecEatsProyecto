package com.teceats.backend.service;

import com.teceats.backend.model.Admin;
import com.teceats.backend.model.RestaurantOwner;
import com.teceats.backend.model.User;
import com.teceats.backend.repository.AdminRepository;
import com.teceats.backend.repository.RestaurantOwnerRepository;
import com.teceats.backend.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final RestaurantOwnerRepository restaurantOwnerRepository;

    public CustomOAuth2UserService(UserRepository userRepository, AdminRepository adminRepository, RestaurantOwnerRepository restaurantOwnerRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.restaurantOwnerRepository = restaurantOwnerRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        Map<String, Object> attributes = oAuth2User.getAttributes();
        String email = (String) attributes.get("email");

        // Determinar el rol según el correo
        String role = determineRole(email);

        // Crear o actualizar el usuario en la base de datos
            switch (role) {
            case "ROLE_ADMIN" -> {
                Admin admin = adminRepository.findByEmail(email).orElse(new Admin());
                admin.setEmail(email);
                admin.setName((String) attributes.get("name"));
                admin.setPicture((String) attributes.get("picture"));
                adminRepository.save(admin);
            }
            case "ROLE_RESTAURANTE" -> {
                RestaurantOwner restaurantOwner = restaurantOwnerRepository.findByEmail(email).orElse(new RestaurantOwner());
                restaurantOwner.setEmail(email);
                restaurantOwner.setName((String) attributes.get("name"));
                restaurantOwner.setPicture((String) attributes.get("picture"));
                restaurantOwnerRepository.save(restaurantOwner);
            }
            case "ROLE_USUARIO" -> {
                User user = userRepository.findByEmail(email).orElse(new User());
                user.setEmail(email);
                user.setName((String) attributes.get("name"));
                user.setPicture((String) attributes.get("picture"));
                userRepository.save(user);
            }
        }

        // Retorna el usuario autenticado con su rol
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(role));

        return new DefaultOAuth2User(authorities, attributes, "name");
    }

    private String determineRole(String email) {
        if (email.equals("juniorosoriotoribio6@gmail.com")) {
            return "ROLE_ADMIN";
        } else if (email.endsWith("@tecsup.edu.pe")) {
            return "ROLE_USUARIO";
        } else {
            return "ROLE_RESTAURANTE";
        }
    }
}
