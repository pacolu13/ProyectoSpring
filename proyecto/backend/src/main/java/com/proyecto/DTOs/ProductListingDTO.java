package com.proyecto.DTOs;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.proyecto.models.StateProduct;

public record ProductListingDTO(
        Long id,
        String title,
        String description,
        BigDecimal price,
        Integer stock,
        StateProduct state,
        LocalDateTime createdDate,
        ProductDTO product,
        SellerProductListingDTO seller) {

}
