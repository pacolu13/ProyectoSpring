package com.proyecto.cart.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.proyecto.cart.dto.*;
import com.proyecto.cart.entity.Cart;
import com.proyecto.cartProduct.mapper.CartProductMapper;

@Mapper(componentModel = "spring", uses = CartProductMapper.class)
public interface CartMapper {

    @Mapping(source = "clientId", target = "client.id")
    @Mapping(source = "cartProductsList", target = "productsList")
    Cart toEntity(CartCreateDTO dto);

    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "productsList", target = "cartProductsList")
    CartDTO toDTO(Cart carrito);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "clientId", target = "client.id")
    @Mapping(source = "cartProductsList", target = "productsList")
    Cart updateCarritoFromDto(CartUpdateDTO dto, @MappingTarget Cart entity);

}