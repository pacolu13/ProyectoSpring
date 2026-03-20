package com.proyecto.productListing.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.proyecto.seller.dto.SellerProductListingDTO;

public record ProductListingDTO(
                Long id,
                SellerProductListingDTO seller,
                String name,
                Integer quantity,
                BigDecimal price,
                LocalDateTime creationDate) {

}
