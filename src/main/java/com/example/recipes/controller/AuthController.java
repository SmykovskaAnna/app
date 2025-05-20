package com.example.recipes.controller;

import com.example.recipes.dto.AuthRequest;
import com.example.recipes.dto.AuthResponse;
import com.example.recipes.dto.RegisterRequest;
import com.example.recipes.dto.UserDto;
import com.example.recipes.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getProfile() {
        return ResponseEntity.ok(authService.getCurrentUser());
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        // В JWT автентифікації вихід обробляється на стороні клієнта шляхом видалення токена
        return ResponseEntity.ok("Successfully logged out");
    }
}