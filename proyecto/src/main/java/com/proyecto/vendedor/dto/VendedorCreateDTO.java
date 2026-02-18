package com.proyecto.vendedor.dto;

public record VendedorCreateDTO(
        String nombre,
        String apellido,
        String telefono,
        String email,
        String contrasenia,
        String cuit) {

}
