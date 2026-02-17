package com.proyecto.producto.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.proyecto.producto.dto.*;
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
    public List<ProductoDTO> ObtenerTodosLosProductos() {
        return servicioProducto.obtenerTodosLosProductos();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> ObtenerProductoPorId(@PathVariable Long id) {
        ProductoDTO response = servicioProducto.obtenerProductoPorId(id);
    return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> añadirProducto(@RequestBody ProductoCreateDTO producto) {
        ProductoDTO response = servicioProducto.añadirProducto(producto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizarParcial(
            @PathVariable Long id,
            @RequestBody ProductoUpdateDTO dto) {

        return ResponseEntity.ok(servicioProducto.actualizarProducto(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        servicioProducto.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
