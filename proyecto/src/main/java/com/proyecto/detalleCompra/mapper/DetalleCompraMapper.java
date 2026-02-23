package com.proyecto.detalleCompra.mapper;

import org.mapstruct.Mapper;

import com.proyecto.detalleCompra.dto.DetalleCompraDTO;
import com.proyecto.detalleCompra.entity.DetalleCompra;

@Mapper(componentModel = "spring")
public interface DetalleCompraMapper {

    DetalleCompraDTO toDTO(DetalleCompra detalleCompra);

    DetalleCompra toEntity(DetalleCompraDTO detalleCompraDTO);
}
