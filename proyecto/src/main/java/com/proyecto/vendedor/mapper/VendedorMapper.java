package com.proyecto.vendedor.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.proyecto.vendedor.dto.VendedorCreateDTO;
import com.proyecto.vendedor.dto.VendedorDTO;
import com.proyecto.vendedor.dto.VendedorUpdateDTO;
import com.proyecto.vendedor.entity.Vendedor;

@Mapper(componentModel="spring")
public interface VendedorMapper {

    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rolesLista", ignore = true)
    Vendedor toEntity(VendedorCreateDTO dto);

    List<Vendedor> toEntityList(List<VendedorCreateDTO> dtoList);

    VendedorDTO toDTO(Vendedor vendedor);

    VendedorCreateDTO toCreateDTO(Vendedor vendedor);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rolesLista", ignore = true)
    Vendedor updateProductoFromDto(VendedorUpdateDTO dto, @MappingTarget Vendedor entity);

    List<VendedorDTO> toDTOList(List<Vendedor> vendedor);

    @AfterMapping
    default void setDefaults(@MappingTarget Vendedor vendedor) {
        
        if (vendedor.getFechaRegistro() == null) {
            vendedor.setFechaRegistro(LocalDateTime.now());
        }
        if (vendedor.getActivo() == null) {
            vendedor.setActivo(true);
        }
    }
}
