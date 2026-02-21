package com.proyecto.cliente.mapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.proyecto.carrito.entity.Carrito;
import com.proyecto.cliente.dto.ClienteCreateDTO;
import com.proyecto.cliente.dto.ClienteDTO;
import com.proyecto.cliente.dto.ClienteUpdateDTO;
import com.proyecto.cliente.entity.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
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
    @Mapping(target = "fechaRegistro", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rolesLista", ignore = true)
    @Mapping(target = "carrito", ignore = true)
    @Mapping(target = "saldo", ignore = true)
    Cliente updateProductoFromDto(ClienteUpdateDTO dto, @MappingTarget Cliente entity);

    List<ClienteDTO> toDTOList(List<Cliente> clientes);

    @AfterMapping
    default void setDefaults(@MappingTarget Cliente cliente) {
        if (cliente.getSaldo() == null) {
            cliente.setSaldo(BigDecimal.ZERO);
        }
        if (cliente.getFechaRegistro() == null) {
            cliente.setFechaRegistro(LocalDateTime.now());
        }
        if (cliente.getActivo() == null) {
            cliente.setActivo(true);
        }
        if(cliente.getCarrito() == null){
            cliente.setCarrito(new Carrito());
        }
    }

}
