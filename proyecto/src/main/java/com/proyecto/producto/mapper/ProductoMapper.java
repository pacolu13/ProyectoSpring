package com.proyecto.producto.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.proyecto.producto.dto.ProductoCreateDTO;
import com.proyecto.producto.dto.ProductoDTO;
import com.proyecto.producto.dto.ProductoUpdateDTO;
import com.proyecto.producto.entity.Producto;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    Producto toEntity(ProductoCreateDTO dto);

    ProductoDTO toDTO(Producto producto);

    ProductoCreateDTO toCreateDTO(Producto producto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Producto updateProductoFromDto(ProductoUpdateDTO dto, @MappingTarget Producto entity);

    List<ProductoDTO> toDTOList(List<Producto> productos);
    
    @AfterMapping
    default void setDefaults(@MappingTarget Producto producto) {
        if (producto.getActivo() == null) {
            producto.setActivo(true);
        }
        if (producto.getFechaCreacion() == null) {
            producto.setFechaCreacion(LocalDateTime.now());
        }
    }

}
