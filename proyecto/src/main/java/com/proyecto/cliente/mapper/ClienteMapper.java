package com.proyecto.cliente.mapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.proyecto.cliente.dto.ClienteCreateDTO;
import com.proyecto.cliente.dto.ClienteDTO;
import com.proyecto.cliente.entity.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteDTO toDTO(Cliente cliente);

    Cliente toEntity(ClienteCreateDTO dto);

    List<ClienteDTO> toDTOList(List<Cliente> clientes);

    @AfterMapping
    default void setDefaults(@MappingTarget Cliente cliente) {
        if (cliente.getSaldo() == null) {
            cliente.setSaldo(BigDecimal.ZERO);
        }
        if (cliente.getFechaRegistro() == null) {
            cliente.setFechaRegistro(LocalDateTime.now());
        }
    }


}

