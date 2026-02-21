package com.proyecto.carrito.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.carrito.dto.CarritoDTO;
import com.proyecto.carrito.service.ServicioCarrito;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/carritos")
public class CarritoController {

    private final ServicioCarrito servicioCarrito;

    public CarritoController(ServicioCarrito servicioCarrito){
        this.servicioCarrito = servicioCarrito;
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<CarritoDTO> obtenerCarrito(@PathVariable Long clienteId) {
        CarritoDTO carrito = servicioCarrito.obtenerCarrito(clienteId);
        return ResponseEntity.ok(carrito);
    }
    
}
