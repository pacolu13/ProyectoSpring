package com.proyecto.DTOs;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductListingUpdateDTO(
        UUID sellerId,
        Long productId,
        Integer quantity,
        BigDecimal price) {

}
