package com.proyecto.user.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UserDTO(
    String id,
    String username,
    String email,
    LocalDateTime creationDate,
    Boolean active,
    BigDecimal balance
) {

}
