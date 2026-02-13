package com.proyecto.catalogo.dataprovider;

import java.math.BigDecimal;
import java.util.List;

import com.proyecto.catalogo.dto.CatalogoDTO;
import com.proyecto.catalogo.entity.Catalogo;
import com.proyecto.producto.entity.Producto;

public class DataProvider {

    public static Catalogo catalogoMock() {
        Catalogo catalogo = new Catalogo();
        catalogo.setId(1L);
        catalogo.setIdVendedor(1L);
        catalogo.setPrecio(BigDecimal.valueOf(100));
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
                new CatalogoDTO(1L, 1L, "Producto 1", "Categoría 1", BigDecimal.valueOf(100), 10),
                new CatalogoDTO(2L, 2L, "Producto 2", "Categoría 2", BigDecimal.valueOf(200), 20),
                new CatalogoDTO(3L, 3L, "Producto 3", "Categoría 3", BigDecimal.valueOf(300), 30),
                new CatalogoDTO(4L, 4L, "Coca Cola", "Bebidas", BigDecimal.valueOf(15), 50)
        );
    }

}
