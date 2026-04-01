package com.proyecto.DTOs;

public record UserCreateDTO(
        String username,
        String email,
        String password) {
}
