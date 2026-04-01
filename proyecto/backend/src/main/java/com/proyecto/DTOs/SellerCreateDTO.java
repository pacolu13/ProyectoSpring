package com.proyecto.DTOs;

public record SellerCreateDTO(
        String username,
        String phoneNumber,
        String email,
        String password,
        String cuit) {

}
