package com.proyecto.seller.dto;

public record SellerCreateDTO(
        String userName,
        String phoneNumber,
        String email,
        String password,
        String cuit) {

}
