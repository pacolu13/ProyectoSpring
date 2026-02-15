package com.proyecto.producto.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.producto.entity.Producto;
import com.proyecto.producto.service.ServicioProducto;



// Controlador para manejar las operaciones relacionadas con los productos
@CrossOrigin(origins = "*", allowedHeaders = "*") // Permite solicitudes desde cualquier origen (útil para desarrollo, pero revisar en producción)
@RestController
@RequestMapping("/productos")
public class ControladorProducto {

    private final ServicioProducto servicioProducto;
    public ControladorProducto(ServicioProducto servicioProducto) {
        this.servicioProducto = servicioProducto;
    }

    @GetMapping
    public List<Producto> ObtenerTodosLosProductos() {
        return servicioProducto.obtenerTodosLosProductos();
    }
    
    @GetMapping("/{id}")
    public Producto ObtenerProductoPorId(@PathVariable Long id) {
        return servicioProducto.obtenerProductoPorId(id);
    }

    /* 
    @PostMapping
    public ResponseEntity<ProductoDTO> añadirProducto(@RequestBody Producto producto) {
        ProductoDTO producto = servicioProducto.añadirProducto(producto);
    }*/
    
}
