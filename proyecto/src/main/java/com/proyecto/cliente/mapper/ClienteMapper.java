package com.proyecto.cliente.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.proyecto.cliente.dto.ClienteCreateDTO;
import com.proyecto.cliente.dto.ClienteDTO;
import com.proyecto.cliente.dto.ClienteUpdateDTO;
import com.proyecto.cliente.entity.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rolesLista", ignore = true)
    @Mapping(target = "carrito", ignore = true)
    @Mapping(target = "saldo", ignore = true)
    Cliente toEntity(ClienteCreateDTO dto);

    List<Cliente> toEntityList(List<ClienteCreateDTO> dtoList);

    ClienteDTO toDTO(Cliente cliente);

    ClienteCreateDTO toCreateDTO(Cliente cliente);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rolesLista", ignore = true)
    @Mapping(target = "carrito", ignore = true)
    @Mapping(target = "saldo", ignore = true)
    Cliente updateProductoFromDto(ClienteUpdateDTO dto, @MappingTarget Cliente entity);

    List<ClienteDTO> toDTOList(List<Cliente> clientes);
}