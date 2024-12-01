package com.teceats.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RestaurantController {
    @GetMapping("/restaurant/home")
    public String restaurantHome() {
        return "restaurant-home";
    }
}
