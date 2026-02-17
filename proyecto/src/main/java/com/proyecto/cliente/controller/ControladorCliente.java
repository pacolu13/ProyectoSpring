package com.proyecto.cliente.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyecto.cliente.dto.*;
import com.proyecto.cliente.service.ServicioCliente;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/clientes")
public class ControladorCliente {

    private final ServicioCliente servicioCliente;

    public ControladorCliente(ServicioCliente servicioCliente) {
        this.servicioCliente = servicioCliente;
    }

    @GetMapping
    public List<ClienteDTO> obtenerClientes() {
        return servicioCliente.obtenerClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtenerClientePorId(@PathVariable Long id) {
        ClienteDTO cliente = servicioCliente.obtenerClientePorId(id);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> añadirCliente(@RequestBody ClienteCreateDTO cliente) {
        ClienteDTO resultado = servicioCliente.añadirCliente(cliente);
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizarParcial(
            @PathVariable Long id,
            @RequestBody ClienteUpdateDTO dto) {

        return ResponseEntity.ok(servicioCliente.actualizarCliente(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        servicioCliente.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
