package com.proyecto.DataProvider;

import java.util.List;

import com.proyecto.DTOs.CatalogoDTO;
import com.proyecto.Entities.Catalogo;
import com.proyecto.Entities.Producto;

public class DataProvider {

    public static Catalogo catalogoMock() {
        Catalogo catalogo = new Catalogo();
        catalogo.setId(1L);
        catalogo.setIdVendedor(1L);
        catalogo.setPrecio(100.0);
        catalogo.setStock(10);
        catalogo.setProducto(productoMock());
        return catalogo;
    }

    public static Producto productoMock() {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Producto de prueba");
        producto.setCategoria("Categoría de prueba");
        producto.setDescripcion("Descripción de prueba");
        return producto;
    }

    public static List<CatalogoDTO> listaCatalogosMock() {
        return List.of(
                new CatalogoDTO(1L, 100.0, 10),
                new CatalogoDTO(2L, 200.0, 20),
                new CatalogoDTO(3L, 300.0, 30)
        );
    }

}
