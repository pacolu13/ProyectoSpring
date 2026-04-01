package com.proyecto.DTOs;

import java.util.List;
import java.util.UUID;

public record CartCreateDTO(
        Long id,
        UUID userId,
        List<CartProductDTO> cartProductsList) {

}
