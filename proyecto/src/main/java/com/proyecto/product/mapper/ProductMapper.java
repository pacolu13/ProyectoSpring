package com.proyecto.product.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.proyecto.product.dto.*;
import com.proyecto.product.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toDTO(Product response);

    List<ProductDTO> toDTOList(List<Product> saveAll);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "productListingList", ignore = true)
    Product toEntity(ProductCreateDTO dto);

    List<Product> toEntityList(List<ProductCreateDTO> productos);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "productListingList", ignore = true)
    void updateProductFromDto(ProductUpdateDTO dto, @MappingTarget Product existingProduct);

}
