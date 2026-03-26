package com.proyecto.cart.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.proyecto.cart.dto.*;
import com.proyecto.cart.entity.Cart;
import com.proyecto.cartProduct.mapper.CartProductMapper;

@Mapper(componentModel = "spring", uses = CartProductMapper.class)
public interface CartMapper {

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "cartProductsList", target = "productsList")
    Cart toEntity(CartCreateDTO dto);

    @Mapping(source = "productsList", target = "cartProductsList")
    CartDTO toDTO(Cart carrito);

}