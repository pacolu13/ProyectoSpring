package com.proyecto.client.dto;

public record ClientCreateDTO(
    String name,
    String lastName,
    String phoneNumber,
    String email,
    String password
) {}