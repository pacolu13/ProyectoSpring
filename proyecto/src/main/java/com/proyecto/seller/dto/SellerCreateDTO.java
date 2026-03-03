package com.proyecto.seller.dto;

public record SellerCreateDTO(
        String username,
        String phoneNumber,
        String email,
        String password,
        String cuit) {

}
