package com.proyecto.vendedor.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.proyecto.vendedor.dto.VendedorDTO;
import com.proyecto.vendedor.entity.Vendedor;

@Component
public class VendedorMapper {

    // Entity → DTO
    public VendedorDTO toDTO(Vendedor vendedor) {
        if (vendedor == null)
            return null;

        VendedorDTO dto = new VendedorDTO();
        dto.setId(vendedor.getId());
        dto.setNombre(vendedor.getNombre());
        dto.setEmail(vendedor.getEmail());
        dto.setCuit(vendedor.getCuit());
        dto.setActivo(vendedor.isActivo());
        dto.setFechaRegistro(vendedor.getFechaRegistro());

        return dto;
    }

    // DTO → Entity
    public Vendedor toEntity(VendedorDTO dto) {
        if (dto == null)
            return null;

        Vendedor vendedor = new Vendedor();
        vendedor.setId(dto.getId());
        vendedor.setNombre(dto.getNombre());
        vendedor.setEmail(dto.getEmail());
        vendedor.setCuit(dto.getCuit());
        vendedor.setActivo(dto.getActivo());
        vendedor.setFechaRegistro(dto.getFechaRegistro());

        return vendedor;
    }

    // Lista Entity → Lista DTO
    public List<VendedorDTO> toDTOList(List<Vendedor> vendedores) {
        return vendedores.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
