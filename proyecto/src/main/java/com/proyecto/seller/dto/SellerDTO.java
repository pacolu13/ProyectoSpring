package com.proyecto.seller.dto;

import java.time.LocalDateTime;

public record SellerDTO(
        Long id,
        String name,
        String email,
        String cuit,
        Boolean active,
        LocalDateTime creationDate) {

}
