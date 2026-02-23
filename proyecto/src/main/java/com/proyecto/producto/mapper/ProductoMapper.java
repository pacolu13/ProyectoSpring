package com.proyecto.producto.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.proyecto.producto.dto.ProductoCreateDTO;
import com.proyecto.producto.dto.ProductoDTO;
import com.proyecto.producto.dto.ProductoUpdateDTO;
import com.proyecto.producto.entity.Producto;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    Producto toEntity(ProductoCreateDTO dto);

    List<Producto> toEntityList(List<ProductoCreateDTO> listDTO);

    ProductoDTO toDTO(Producto producto);

    ProductoCreateDTO toCreateDTO(Producto producto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    Producto updateProductoFromDto(ProductoUpdateDTO dto, @MappingTarget Producto entity);

    List<ProductoDTO> toDTOList(List<Producto> productos);

}
