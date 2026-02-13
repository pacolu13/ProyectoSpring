package com.proyecto.catalogo.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.catalogo.dto.CatalogoDTO;
import com.proyecto.catalogo.entity.Catalogo;
import com.proyecto.catalogo.repository.RepoCatalogo;
import com.proyecto.catalogo.spec.BusquedaCatalogo;

@Service
public class ServicioCatalogo {

    private final RepoCatalogo repoCatalogo;

    public ServicioCatalogo(RepoCatalogo repoCatalogo) {
        this.repoCatalogo = repoCatalogo;
    }

    // Si esta vacia tirar una excepcion?
    public List<CatalogoDTO> obtenerCatalogo() {
        List<Catalogo> catalogo = repoCatalogo.findAll();
        
        if (catalogo == null || catalogo.isEmpty()) {
            throw new RuntimeException("Catálogo vacío");
        }
        return catalogo.stream().map(c -> {
            return new CatalogoDTO(c.getId(), c.getProducto().getId(), c.getProducto().getNombre(),
                    c.getProducto().getCategoria(), c.getPrecio(), c.getStock());
        }).toList();
    }

    public CatalogoDTO obtenerCatalogoPorId(Long id) {
        Catalogo catalogo = repoCatalogo.findById(id).orElseThrow(() -> new RuntimeException("Catálogo no encontrado"));
        return new CatalogoDTO(catalogo.getId(), catalogo.getProducto().getId(), catalogo.getProducto().getNombre(),
                catalogo.getProducto().getCategoria(), catalogo.getPrecio(), catalogo.getStock());
    }

    public List<CatalogoDTO> obtenerCatalogoPorFiltro(String nombre, String categoria, BigDecimal precioMin,
            BigDecimal precioMax) {
        BusquedaCatalogo busqueda = new BusquedaCatalogo(nombre, categoria, precioMin, precioMax);
        List<Catalogo> catalogo = repoCatalogo.findAll(busqueda);
        if (catalogo == null || catalogo.isEmpty()) {
            throw new RuntimeException("No se encontraron productos con los filtros especificados");
        }
        return catalogo.stream().map(c -> {
            return new CatalogoDTO(c.getId(), c.getProducto().getId(), c.getProducto().getNombre(),
                    c.getProducto().getCategoria(), c.getPrecio(), c.getStock());
        }).toList();
    }

    public CatalogoDTO crearCatalogo(Catalogo catalogo) {
        Catalogo nuevoCatalogo = repoCatalogo.save(catalogo);
        return new CatalogoDTO(nuevoCatalogo.getId(), nuevoCatalogo.getProducto().getId(),
                nuevoCatalogo.getProducto().getNombre(), nuevoCatalogo.getProducto().getCategoria(),
                nuevoCatalogo.getPrecio(),
                nuevoCatalogo.getStock());
    }

    // Todavia no vamos a darle la posibilidad de editar catalogos

    public void eliminarCatalogo(Long id) {
        if (!repoCatalogo.existsById(id)) {
            throw new RuntimeException("Catálogo no encontrado");
        }
        repoCatalogo.deleteById(id);
    }
}
