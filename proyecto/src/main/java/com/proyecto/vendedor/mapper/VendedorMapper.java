package com.proyecto.vendedor.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.proyecto.vendedor.dto.VendedorDTO;
import com.proyecto.vendedor.entity.Vendedor;

@Mapper(componentModel = "spring")
public interface VendedorMapper {

    public Vendedor toEntity(VendedorDTO dto);

    public VendedorDTO toDTO(Vendedor vendedor);

    public VendedorCreateDTO toCreateDTO(Vendedor vendedor);

    public VendedorUpdateDTO toUpdateDTO(Vendedor vendedor);

    public List<VendedorDTO> toDTOList(List<Vendedor> vendedores);
}
