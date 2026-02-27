package com.proyecto.client.dto;

public record ClientCreateDTO(
    String userName,
    String phoneNumber,
    String email,
    String password
) {}