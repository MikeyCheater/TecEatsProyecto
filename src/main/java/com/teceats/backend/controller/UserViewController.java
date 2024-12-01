package com.teceats.backend.controller;

import com.teceats.backend.model.User;
import com.teceats.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserViewController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Muestra la vista con todos los usuarios registrados.
     */
    @GetMapping
    public String showUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users"; // Nombre del archivo HTML sin la extensión
    }
}
