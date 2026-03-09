package com.proyecto.orderDetails.dto;

import java.math.BigDecimal;

public record OrderDetailsDTO(
    Long id,
    String productName,
    Integer quantity,
    BigDecimal unitPrice
) {

}
