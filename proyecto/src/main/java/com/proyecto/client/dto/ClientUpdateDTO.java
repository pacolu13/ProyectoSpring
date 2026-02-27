package com.proyecto.client.dto;

public record ClientUpdateDTO(
    String userName,
    String phoneNumber,
    String email,
    String password
) {}