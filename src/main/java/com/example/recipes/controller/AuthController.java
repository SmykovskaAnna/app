package com.example.recipes.controller;

import com.example.recipes.model.User;
import com.example.recipes.service.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserDetailsServiceImpl userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user,
                               BindingResult result,
                               Model model) {

        // Check for validation errors
        if (result.hasErrors()) {
            return "register";
        }

        // Check if username exists
        if (userService.existsByUsername(user.getUsername())) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "register";
        }

        // Check if email exists
        if (userService.existsByEmail(user.getEmail())) {
            model.addAttribute("emailError", "Пользователь с таким email уже существует");
            return "register";
        }

        // Save user to database
        userService.save(user);

        return "redirect:/login?success";
    }
}