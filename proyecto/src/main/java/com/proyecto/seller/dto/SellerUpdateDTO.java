package com.proyecto.seller.dto;

public record SellerUpdateDTO(
        String name,
        String lastName,
        String phoneNumber,
        String email,
        String password,
        String cuit) {

}
