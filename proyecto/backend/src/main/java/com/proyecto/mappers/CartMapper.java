package com.proyecto.mappers;

import org.mapstruct.Mapper;

import com.proyecto.DTOs.CartDTO;
import com.proyecto.models.Cart;

@Mapper(componentModel = "spring", uses = CartProductMapper.class)
public interface CartMapper {

    CartDTO toDTO(Cart cart);

}
