package com.proyecto.client.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.proyecto.client.dto.ClientCreateDTO;
import com.proyecto.client.dto.ClientDTO;
import com.proyecto.client.dto.ClientUpdateDTO;
import com.proyecto.client.entity.Client;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(target = "active", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rolesList", ignore = true)
    @Mapping(target = "cart", ignore = true)
    @Mapping(target = "balance", ignore = true)
    Client toEntity(ClientCreateDTO dto);

    List<Client> toEntityList(List<ClientCreateDTO> dtoList);

    ClientDTO toDTO(Client cliente);

    ClientCreateDTO toCreateDTO(Client cliente);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rolesList", ignore = true)
    @Mapping(target = "cart", ignore = true)
    @Mapping(target = "balance", ignore = true)
    Client updateClientFromDto(ClientUpdateDTO dto, @MappingTarget Client entity);

    List<ClientDTO> toDTOList(List<Client> clientes);
}