package com.proyecto.order.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.proyecto.orderDetails.dto.OrderDetailsDTO;

public record OrderDTO(
        String clientUsername,
        LocalDateTime orderDate,
        List<OrderDetailsDTO> orderDetailsList,
        BigDecimal totalBalance) {

}
