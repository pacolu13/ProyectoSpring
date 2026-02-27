package com.proyecto.productListing.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.productListing.dto.ProductListingCreateDTO;
import com.proyecto.productListing.dto.ProductListingDTO;
import com.proyecto.productListing.service.ProductListingService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/productosventa")
public class ProductListingController {

    private final ProductListingService servicioProductoVenta;

    public ProductListingController(ProductListingService servicioProductoVenta) {
        this.servicioProductoVenta = servicioProductoVenta;
    }

    @GetMapping
    public List<ProductListingDTO> obtenerProductosVenta() {
        return servicioProductoVenta.findAllProductsListing();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductListingDTO> obtenerProductoVentaPorId(@PathVariable Long id) {
        ProductListingDTO response = servicioProductoVenta.findyProductListingById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<List<ProductListingDTO>> añadirListaProductosVenta(
            @RequestBody List<ProductListingCreateDTO> listaProductosVenta) {
        List<ProductListingDTO> response = servicioProductoVenta.addProductListingList(listaProductosVenta);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
