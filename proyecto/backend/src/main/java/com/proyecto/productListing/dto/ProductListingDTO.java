package com.proyecto.productListing.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductListingDTO(
                Long id,
                String sellerName,
                String productName,
                Integer quantity,
                BigDecimal price,
                LocalDateTime creationDate) {

}
