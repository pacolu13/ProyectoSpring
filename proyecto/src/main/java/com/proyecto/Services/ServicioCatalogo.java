package com.proyecto.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.DTOs.CatalogoDTO;
import com.proyecto.Entities.Catalogo;
import com.proyecto.Repositories.RepoCatalogo;

@Service
public class ServicioCatalogo {

    private final RepoCatalogo repoCatalogo;

    public ServicioCatalogo(RepoCatalogo repoCatalogo) {
        this.repoCatalogo = repoCatalogo;
    }

    public List<CatalogoDTO> obtenerCatalogo() {
        List<Catalogo> catalogo = repoCatalogo.findAll();
        return catalogo.stream().map(c -> {
            return new CatalogoDTO(c.getProducto().getId(), c.getPrecio(), c.getStock());
        }).toList();
    }

    public CatalogoDTO obtenerCatalogoPorId(Long id) {
        Catalogo catalogo = repoCatalogo.findById(id).orElseThrow(() -> new RuntimeException("Catálogo no encontrado"));
        return new CatalogoDTO(catalogo.getProducto().getId(), catalogo.getPrecio(), catalogo.getStock());
    }

    public CatalogoDTO crearCatalogo(Catalogo catalogo) {
        Catalogo nuevoCatalogo = repoCatalogo.save(catalogo);
        return new CatalogoDTO(nuevoCatalogo.getProducto().getId(), nuevoCatalogo.getPrecio(), nuevoCatalogo.getStock());
    }

    //Todavia no vamos a darle la posibilidad de editar catalogos

    public void eliminarCatalogo(Long id) {
        if (!repoCatalogo.existsById(id)) {
            throw new RuntimeException("Catálogo no encontrado");
        }
        repoCatalogo.deleteById(id);
    }
}
