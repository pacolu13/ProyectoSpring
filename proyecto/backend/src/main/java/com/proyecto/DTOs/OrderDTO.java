package com.proyecto.DTOs;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO(
        Long id,
        String clientUsername,
        LocalDateTime orderDate,
        List<OrderDetailsDTO> orderDetailsList,
        BigDecimal totalBalance) {

}
