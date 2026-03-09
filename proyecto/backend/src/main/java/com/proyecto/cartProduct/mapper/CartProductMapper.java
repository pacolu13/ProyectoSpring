package com.proyecto.cartProduct.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.proyecto.cartProduct.dto.CartProductDTO;
import com.proyecto.cartProduct.entity.CartProduct;

@Mapper(componentModel = "spring")
public interface CartProductMapper {

    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "productListing.id", target = "productListingId")
    @Mapping(source = "productListing.product.name", target = "name")
    CartProductDTO toDTO(CartProduct carrito);

    //vamos a ignorar el carrito y el id porque no los necesitamos para crear un nuevo CartProduct
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cart", ignore = true)
    @Mapping(target = "productListing", ignore = true)
    CartProduct toEntity(CartProductDTO dto);

    List<CartProductDTO> toDTOList(List<CartProduct> entityList);

    List<CartProduct> toEntityList(List<CartProductDTO> dtoList);

}