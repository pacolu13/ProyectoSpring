package com.proyecto.cliente.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.cliente.dto.ClienteCreateDTO;
import com.proyecto.cliente.dto.ClienteDTO;
import com.proyecto.cliente.service.ServicioCliente;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
    public ClienteDTO obtenerClientePorId(@PathVariable Long id) {
        return servicioCliente.obtenerClientePorId(id);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> añadirCliente(@Valid @RequestBody ClienteCreateDTO cliente) {
        System.out.println(cliente);
        ClienteDTO resultado = servicioCliente.añadirCliente(cliente);
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }
}
