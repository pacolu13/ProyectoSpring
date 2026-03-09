package com.proyecto.seller.dto;

public record SellerUpdateDTO(
        String username,
        String phoneNumber,
        String email,
        String password,
        String cuit) {

}
