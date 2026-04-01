package com.proyecto.models;

import java.util.List;
import java.util.UUID;

import com.proyecto.DTOs.CartProductDTO;

public record CartCreateDTO(
                Long id,
                UUID userId,
                List<CartProductDTO> cartProductsList) {

}
