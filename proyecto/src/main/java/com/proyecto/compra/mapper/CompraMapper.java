package com.proyecto.compra.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.proyecto.compra.dto.CompraDTO;
import com.proyecto.compra.entity.Compra;
import com.proyecto.detalleCompra.mapper.DetalleCompraMapper;

@Mapper(componentModel = "spring", uses = DetalleCompraMapper.class)
public interface CompraMapper {

    @Mapping(source = "compra.cliente.nombre", target = "nombreCliente")
    @Mapping(source = "compra.cliente.apellido", target = "apellidoCliente")
    @Mapping(source = "compra.total", target = "total")
    @Mapping(source = "compra.fecha", target = "fecha")
    CompraDTO toDTO(Compra compra);

    Compra toEntity(CompraDTO compraDTO);

}
