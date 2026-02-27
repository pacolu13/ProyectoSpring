package com.proyecto.client.dto;

public record ClientUpdateDTO(
    String name,
    String lastName,
    String phoneNumber,
    String email,
    String password
) {}