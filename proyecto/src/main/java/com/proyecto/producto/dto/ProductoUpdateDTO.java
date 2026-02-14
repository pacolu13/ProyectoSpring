package com.proyecto.producto.dto;

public class ProductoUpdateDTO extends ProductoAbstractDTO {

    public ProductoUpdateDTO() {
    }

    public ProductoUpdateDTO(Long id, String nombre,
            String descripcion, String marca, String categoria, String imagen) {
        setId(id);
        setNombre(nombre);
        setDescripcion(descripcion);
        setMarca(marca);
        setCategoria(categoria);
        setImagen(imagen);
    }
}
