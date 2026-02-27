package com.proyecto.product.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.proyecto.product.dto.ProductCreateDTO;
import com.proyecto.product.dto.ProductDTO;
import com.proyecto.product.dto.ProductUpdateDTO;
import com.proyecto.product.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toDTO(Product response);

    List<Product> toEntityList(List<ProductCreateDTO> productos);

    List<ProductDTO> toDTOList(List<Product> saveAll);

    void updateProductFromDto(ProductUpdateDTO dto, @MappingTarget Product existingProduct);

}
