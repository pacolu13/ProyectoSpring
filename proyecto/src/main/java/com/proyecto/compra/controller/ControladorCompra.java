package com.proyecto.compra.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.compra.dto.CompraDTO;
import com.proyecto.compra.service.ServicioCompra;

import org.springframework.web.bind.annotation.PostMapping;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/compras")
public class ControladorCompra {

    private final ServicioCompra servicioCompra;

    public ControladorCompra(ServicioCompra servicioCompra) {
        this.servicioCompra = servicioCompra;
    }
    @PostMapping("/confirmar/{clienteId}")
    public ResponseEntity<CompraDTO> confirmarCompra(@PathVariable Long clienteId) {
        CompraDTO compraDTO = servicioCompra.confirmarCompra(clienteId);
        return ResponseEntity.ok(compraDTO);
    }
}
