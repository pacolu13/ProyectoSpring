package com.proyecto.productoVenta.controller;

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

import com.proyecto.productoVenta.dto.ProductoVentaCreateDTO;
import com.proyecto.productoVenta.dto.ProductoVentaDTO;
import com.proyecto.productoVenta.service.ServicioProductoVenta;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/productosventa")
public class ControladorProductoVenta {

    private final ServicioProductoVenta servicioProductoVenta;

    public ControladorProductoVenta(ServicioProductoVenta servicioProductoVenta) {
        this.servicioProductoVenta = servicioProductoVenta;
    }

    @GetMapping
    public List<ProductoVentaDTO> obtenerProductosVenta() {
        return servicioProductoVenta.obtenerProductosVentas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoVentaDTO> obtenerProductoVentaPorId(@PathVariable Long id) {
        ProductoVentaDTO response = servicioProductoVenta.obtenerProductoVentaPorId(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<List<ProductoVentaDTO>> añadirListaProductosVenta(
            @RequestBody List<ProductoVentaCreateDTO> listaProductosVenta) {
        List<ProductoVentaDTO> response = servicioProductoVenta.añadirListaProductoVenta(listaProductosVenta);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
