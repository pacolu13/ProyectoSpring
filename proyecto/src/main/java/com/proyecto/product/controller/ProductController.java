package com.proyecto.product.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyecto.product.dto.*;
import com.proyecto.product.service.ProductService;

import lombok.RequiredArgsConstructor;

// Controlador para manejar las operaciones relacionadas con los productos
@CrossOrigin(origins = "*", allowedHeaders = "*") // Permite solicitudes desde cualquier origen (útil para desarrollo,
                                                  // pero revisar en producción)
@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor

public class ProductController {

    private final ProductService servicioProducto;

    @GetMapping
    public List<ProductDTO> ObtenerTodosLosProductos() {
        return servicioProducto.obtenerTodosLosProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> ObtenerProductoPorId(@PathVariable Long id) {
        ProductDTO response = servicioProducto.obtenerProductoPorId(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<List<ProductDTO>> añadirListaProducto(@RequestBody List<ProductCreateDTO> listaProductos) {
        List<ProductDTO> response = servicioProducto.añadirListaProducto(listaProductos);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> actualizarParcial(
            @PathVariable Long id,
            @RequestBody ProductUpdateDTO dto) {

        servicioProducto.actualizarProducto(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        servicioProducto.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
