package com.proyecto.producto.mapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.proyecto.producto.dto.ProductoCreateDTO;
import com.proyecto.producto.dto.ProductoDTO;
import com.proyecto.producto.dto.ProductoUpdateDTO;
import com.proyecto.producto.entity.Producto;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    ProductoDTO toDTO(Producto producto);

    ProductoCreateDTO toCreateDTO(Producto producto);

    ProductoUpdateDTO toUpdateDTO(Producto producto);

    Producto toEntity(ProductoCreateDTO dto);

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
