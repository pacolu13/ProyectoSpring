package com.proyecto.vendedor.mapper;

public record VendedorCreateDTO(
        Long id,
        String nombre,
        String email,
        String cuit) {

}
