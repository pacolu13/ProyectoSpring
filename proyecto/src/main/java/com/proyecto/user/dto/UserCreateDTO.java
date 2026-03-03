package com.proyecto.user.dto;

public record UserCreateDTO(
    String username,
    String email,
    String password
) {

}
