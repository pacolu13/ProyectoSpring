package com.proyecto.cartProduct.dto;

public record CartProductDTO(
        Long id,
        Long productListingId,
        String name,
        Integer quantity) {

}
