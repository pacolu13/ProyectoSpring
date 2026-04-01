package com.proyecto.DTOs;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductListingDTO(
                Long id,
                SellerProductListingDTO seller,
                String name,
                Integer quantity,
                BigDecimal price,
                LocalDateTime creationDate) {

}
