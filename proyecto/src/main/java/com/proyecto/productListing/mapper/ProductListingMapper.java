package com.proyecto.productListing.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.proyecto.productListing.dto.ProductListingCreateDTO;
import com.proyecto.productListing.dto.ProductListingDTO;
import com.proyecto.productListing.dto.ProductListingUpdateDTO;
import com.proyecto.productListing.entity.ProductListing;

@Mapper(componentModel = "spring")
public interface ProductListingMapper {

    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "sellerId", target = "seller.id")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderDetails", ignore = true)
    ProductListing toEntity(ProductListingCreateDTO dto);

    
    List<ProductListing> toEntityList(List<ProductListingCreateDTO> dtoList);

    @Mapping(source = "product.name", target = "nameProduct")
    @Mapping(source = "seller.userName", target = "nameSeller")
    ProductListingDTO toDTO(ProductListing entity);

    @Mapping(source = "product.name", target = "nameProduct")
    @Mapping(source = "seller.userName", target = "nameSeller")
    List<ProductListingDTO> toDTOList(List<ProductListing> entityList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderDetails", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "seller", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductListing updateProductFromDto(ProductListingUpdateDTO dto, @MappingTarget ProductListing entity);

}
