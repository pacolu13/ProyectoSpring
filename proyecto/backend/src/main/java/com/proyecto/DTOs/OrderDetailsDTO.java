package com.proyecto.DTOs;

import java.math.BigDecimal;

public record OrderDetailsDTO(
    Long id,
    String productName,
    Integer quantity,
    BigDecimal unitPrice
) {

}
