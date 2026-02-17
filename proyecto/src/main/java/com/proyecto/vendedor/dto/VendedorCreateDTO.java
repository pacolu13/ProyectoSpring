package com.proyecto.vendedor.dto;

public record VendedorCreateDTO(
        Long id,
        String nombre,
        String apellido,
        String telefono,
        String email,
        String contrasenia,
        String cuit) {

}
