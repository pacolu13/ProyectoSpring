package com.proyecto.catalogo.dto;

import java.math.BigDecimal;

public class CatalogoDTO {
    private Long id;
    private Long idProducto;
    private String nombreProducto;
    private String categoriaProducto;
    private BigDecimal precio;
    private Integer stock;
    
    public CatalogoDTO(Long id, Long idProducto, String nombreProducto, String categoriaProducto, BigDecimal precio, Integer stock) {
        this.id = id;
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.categoriaProducto = categoriaProducto;
        this.precio = precio;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public String getCategoriaProducto() {
        return categoriaProducto;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public Integer getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return "CatalogoDTO{" +
                "idProducto=" + idProducto +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CatalogoDTO that = (CatalogoDTO) o;

        if (!idProducto.equals(that.idProducto)) return false;
        if (!nombreProducto.equals(that.nombreProducto)) return false;
        if (!precio.equals(that.precio)) return false;
        return stock.equals(that.stock);
    }
}
