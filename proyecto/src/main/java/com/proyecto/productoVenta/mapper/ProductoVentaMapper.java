package com.proyecto.productoVenta.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.proyecto.productoVenta.dto.ProductoVentaCreateDTO;
import com.proyecto.productoVenta.dto.ProductoVentaDTO;
import com.proyecto.productoVenta.dto.ProductoVentaUpdateDTO;
import com.proyecto.productoVenta.entity.ProductoVenta;

@Mapper(componentModel = "spring")
public interface ProductoVentaMapper {

    @Mapping(source = "productoId", target = "producto.id")
    @Mapping(source = "vendedorId", target = "vendedor.id")
    ProductoVenta toEntity(ProductoVentaCreateDTO dto);

    List<ProductoVenta> toEntityList(List<ProductoVentaCreateDTO> dtoList);

    @Mapping(source = "producto.id", target = "productoId")
    @Mapping(source = "vendedor.id", target = "vendedorId")
    ProductoVentaDTO toDTO(ProductoVenta entity);

    List<ProductoVentaDTO> toDTOlList(List<ProductoVenta> entityList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductoVenta updateProductoFromDto(ProductoVentaUpdateDTO dto, @MappingTarget ProductoVenta entity);

}
