package com.proyecto.mappers;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.proyecto.DTOs.ProductListingCreateDTO;
import com.proyecto.DTOs.ProductListingDTO;
import com.proyecto.DTOs.ProductListingUpdateDTO;
import com.proyecto.models.ProductListing;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface ProductListingMapper {

    ProductListing toEntity(ProductListingCreateDTO dto);

    List<ProductListing> toEntityList(List<ProductListingCreateDTO> dtoList);

    @Mapping(source = "user" , target = "seller")
    ProductListingDTO toDTO(ProductListing entity);

    @Mapping(source = "product.name", target = "productName")
    @Mapping(source = "user.username", target = "sellerName")
    List<ProductListingDTO> toDTOList(List<ProductListing> entityList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderDetails", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "user", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductListing updateProductFromDto(ProductListingUpdateDTO dto, @MappingTarget ProductListing entity);
}
