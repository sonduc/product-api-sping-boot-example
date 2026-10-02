package com.example.productapi.dto;

public record AuthResponse(String token, String username, String role, long expiresIn) {
}
