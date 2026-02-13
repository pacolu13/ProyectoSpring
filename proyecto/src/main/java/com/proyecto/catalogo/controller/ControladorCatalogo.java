package com.proyecto.catalogo.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.catalogo.dto.CatalogoDTO;
import com.proyecto.catalogo.service.ServicioCatalogo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin(origins = "*", allowedHeaders = "*") // Permite solicitudes desde cualquier origen (útil para desarrollo, pero revisar en producción)
@RestController
@RequestMapping("/catalogo")
public class ControladorCatalogo {

    private final ServicioCatalogo servicioCatalogo;

    public ControladorCatalogo(ServicioCatalogo servicioCatalogo) {
        this.servicioCatalogo = servicioCatalogo;
    }

    @GetMapping
    public List<CatalogoDTO> ObtenerCatalogo() {
        return servicioCatalogo.obtenerCatalogo();    
    }

    @GetMapping("/{id}")
    public CatalogoDTO ObtenerCatalogoPorId(@PathVariable Long id) {
        return servicioCatalogo.obtenerCatalogoPorId(id);
    }

    @GetMapping("/filtro")
    public List<CatalogoDTO> ObtenerCatalogoPorFiltro(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) BigDecimal precioMin,
            @RequestParam(required = false) BigDecimal precioMax) {
        return servicioCatalogo.obtenerCatalogoPorFiltro(nombre, categoria, precioMin, precioMax);
    }   

}
