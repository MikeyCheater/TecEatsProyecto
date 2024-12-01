package com.teceats.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/user/home")
    public String userHome() {
        return "user-home";
    }
}
