package com.proyecto.compra.mapper;

import org.mapstruct.Mapper;

import com.proyecto.compra.dto.CompraDTO;
import com.proyecto.compra.entity.Compra;

@Mapper(componentModel = "spring")
public interface CompraMapper {

    CompraDTO toDTO(Compra compra);

    Compra toEntity(CompraDTO compraDTO);

}
