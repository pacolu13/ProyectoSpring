package com.proyecto.cart.dto;

import java.util.UUID;

public record CartAddProductDTO(
        UUID clientId,
        Long productListingId,
        Integer quantity) {

}
