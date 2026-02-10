package com.proyecto.DTOs;

public class CatalogoDTO {
    private Long idProducto;
    private Double precio;
    private Integer stock;
    
    public CatalogoDTO(Long idProducto, Double precio, Integer stock) {
        this.idProducto = idProducto;
        this.precio = precio;
        this.stock = stock;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public Double getPrecio() {
        return precio;
    }

    public Integer getStock() {
        return stock;
    }
}
