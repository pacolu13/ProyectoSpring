package com.proyecto.carrito.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.proyecto.carrito.dto.*;
import com.proyecto.carrito.entity.Carrito;
import com.proyecto.productoCarrito.mapper.ProductoCarritoMapper;

@Mapper(componentModel = "spring", uses = ProductoCarritoMapper.class)
public interface CarritoMapper {

    @Mapping(source = "clienteId", target = "cliente.id")
    @Mapping(source = "productosCarrito", target = "productosList")
    Carrito toEntity(CarritoCreateDTO dto);

    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "productosList", target = "productosCarrito")
    CarritoDTO toDTO(Carrito carrito);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "clienteId", target = "cliente.id")
    @Mapping(source = "productosCarrito", target = "productosList")
    Carrito updateCarritoFromDto(CarritoUpdateDTO dto, @MappingTarget Carrito entity);

}