package com.proyecto.detalleCompra.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.proyecto.detalleCompra.dto.DetalleCompraDTO;
import com.proyecto.detalleCompra.entity.DetalleCompra;

@Mapper(componentModel = "spring")
public interface DetalleCompraMapper {

    @Mapping(source = "detalleCompra.productoVenta.producto.nombre", target = "nombreProducto")
    @Mapping(source = "detalleCompra.cantidad", target = "cantidad")
    @Mapping(source = "detalleCompra.precioUnitario", target = "precioUnitario")
    DetalleCompraDTO toDTO(DetalleCompra detalleCompra);

    DetalleCompra toEntity(DetalleCompraDTO detalleCompraDTO);
}
