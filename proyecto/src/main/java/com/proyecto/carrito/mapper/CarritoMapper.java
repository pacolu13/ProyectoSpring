package com.proyecto.carrito.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.proyecto.carrito.dto.*;
import com.proyecto.carrito.entity.Carrito;

@Mapper(componentModel = "spring")
public interface CarritoMapper {

    Carrito toEntity(CarritoCreateDTO dto);

    @Mapping(source = "cliente.id", target = "clienteId")
    CarritoDTO toDTO(Carrito carrito);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Carrito updateCarritoFromDto(CarritoUpdateDTO dto, @MappingTarget Carrito entity);

}