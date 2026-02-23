package com.proyecto.carrito.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.carrito.dto.AgregarProductoCarritoDTO;
import com.proyecto.carrito.dto.CarritoDTO;
import com.proyecto.carrito.service.ServicioCarrito;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/carritos")
public class ControladorCarrito {

    private final ServicioCarrito servicioCarrito;

    public ControladorCarrito(ServicioCarrito servicioCarrito) {
        this.servicioCarrito = servicioCarrito;
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<CarritoDTO> obtenerCarrito(@PathVariable Long clienteId) {
        CarritoDTO carrito = servicioCarrito.obtenerCarrito(clienteId);
        return ResponseEntity.ok(carrito);
    }

    @PostMapping
    public ResponseEntity<CarritoDTO> añadirProductoAlCarrito(@RequestBody AgregarProductoCarritoDTO dto){
        
        System.out.println(dto);
        CarritoDTO response = servicioCarrito.añadirProducto(dto);
        return ResponseEntity.ok(response);
    }

}
