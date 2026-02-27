package com.proyecto.seller.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record SellerDTO(
        UUID id,
        String name,
        String email,
        String cuit,
        Boolean active,
        LocalDateTime creationDate) {

}
