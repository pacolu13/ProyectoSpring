package com.proyecto.cliente.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.cliente.dto.ClienteCreateDTO;
import com.proyecto.cliente.dto.ClienteDTO;
import com.proyecto.cliente.dto.ClienteUpdateDTO;
import com.proyecto.cliente.service.ServicioCliente;

import jakarta.validation.Valid;

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
    public ResponseEntity<ClienteDTO> añadirCliente(@Valid @RequestBody ClienteCreateDTO cliente) {
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
