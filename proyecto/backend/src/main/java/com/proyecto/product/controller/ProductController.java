package com.proyecto.product.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyecto.config.ApiRoutes;
import com.proyecto.product.dto.*;
import com.proyecto.product.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiRoutes.PRODUCTS)
@RequiredArgsConstructor

public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> response = productService.getAllProducts();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> ObtenerProductoPorId(@PathVariable Long id) {
        ProductDTO response = productService.getProductById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<List<ProductDTO>> añadirListaProducto(@RequestBody List<ProductCreateDTO> listaProductos) {
        List<ProductDTO> response = productService.addProductList(listaProductos);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> actualizarParcial(
            @PathVariable Long id,
            @RequestBody ProductUpdateDTO dto) {

        productService.productUpdate(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productService.productDelete(id);
        return ResponseEntity.noContent().build();
    }
}
