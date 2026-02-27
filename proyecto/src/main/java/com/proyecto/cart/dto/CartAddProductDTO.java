package com.proyecto.cart.dto;

public record CartAddProductDTO(
        Long clientId,
        Long productListingId,
        Integer quantity) {

}
