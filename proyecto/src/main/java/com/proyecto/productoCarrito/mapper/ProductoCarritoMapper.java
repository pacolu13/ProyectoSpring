package com.proyecto.productoCarrito.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.proyecto.productoCarrito.dto.ProductoCarritoDTO;
import com.proyecto.productoCarrito.entity.ProductoCarrito;

@Mapper(componentModel = "spring")
public interface ProductoCarritoMapper {

    @Mapping(source = "cantidad", target = "cantidad")
    @Mapping(source = "productoVenta.id", target = "productoVentaId")
    @Mapping(source = "productoVenta.producto.nombre", target = "nombre")
    ProductoCarritoDTO toDTO(ProductoCarrito carrito);

    ProductoCarrito toEntity(ProductoCarritoDTO dto);

    List<ProductoCarritoDTO> toDTOList(List<ProductoCarrito> entityList);

    List<ProductoCarrito> toEntityList(List<ProductoCarritoDTO> dtoList);

}