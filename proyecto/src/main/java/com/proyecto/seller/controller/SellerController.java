package com.proyecto.seller.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.seller.dto.SellerCreateDTO;
import com.proyecto.seller.dto.SellerDTO;
import com.proyecto.seller.service.SellerService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/sellers")
@RequiredArgsConstructor

public class SellerController {

    private final SellerService servicioVendedor;

    @GetMapping
    public List<SellerDTO> obtenerVendedores() {
        return servicioVendedor.obtenerVendedores();
    }

    @GetMapping("/{id}")
    public SellerDTO obtenerVendedorPorId(@PathVariable UUID id) {
        return servicioVendedor.obtenerVendedorPorId(id);
    }

    @GetMapping("/filtrar")
    public List<SellerDTO> obtenerVendedoresPorFiltro(
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String email) {
        return servicioVendedor.obtenerVendedorPorFiltro(nombre, apellido, email);
    }

    @PostMapping
    public ResponseEntity<List<SellerDTO>> añadirListaVendedores(@RequestBody List<SellerCreateDTO> vendedores) {
        List<SellerDTO> response = servicioVendedor.añadirVendedores(vendedores);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVendedor(@PathVariable UUID id) {
        servicioVendedor.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
