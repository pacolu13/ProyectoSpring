package com.proyecto.seller.dto;

public record SellerUpdateDTO(
        String userName,
        String phoneNumber,
        String email,
        String password,
        String cuit) {

}
