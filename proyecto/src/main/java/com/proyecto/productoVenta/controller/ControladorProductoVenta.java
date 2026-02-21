package com.proyecto.productoVenta.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.proyecto.productoVenta.dto.ProductoVentaCreateDTO;
import com.proyecto.productoVenta.dto.ProductoVentaDTO;
import com.proyecto.productoVenta.service.ServicioProductoVenta;

public class ControladorProductoVenta {

    private final ServicioProductoVenta servicioProductoVenta;

    public ControladorProductoVenta(ServicioProductoVenta servicioProductoVenta) {
        this.servicioProductoVenta = servicioProductoVenta;
    }

    @GetMapping
    public List<ProductoVentaDTO> obtenerClientes() {
        return servicioProductoVenta.obtenerProductosVentas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoVentaDTO> obtenerClientePorId(@PathVariable Long id) {
        ProductoVentaDTO cliente = servicioProductoVenta.obtenerProductoVentaPorId(id);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<List<ProductoVentaDTO>> añadirListaClientes(
            @RequestBody List<ProductoVentaCreateDTO> listaProductosVenta) {
        List<ProductoVentaDTO> resultado = servicioProductoVenta.añadirListaProductoVenta(listaProductosVenta);
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }
}
