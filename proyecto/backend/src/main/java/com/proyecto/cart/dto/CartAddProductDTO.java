package com.proyecto.cart.dto;

import java.util.UUID;

public record CartAddProductDTO(
        UUID userId,
        Long productListingId,
        Integer quantity) {

}
