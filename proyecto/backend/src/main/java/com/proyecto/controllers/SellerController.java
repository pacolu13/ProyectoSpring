package com.proyecto.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.DTOs.SellerCreateDTO;
import com.proyecto.DTOs.SellerDTO;
import com.proyecto.config.ApiRoutes;
import com.proyecto.services.SellerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiRoutes.SELLERS)
@RequiredArgsConstructor

public class SellerController {

    private final SellerService servicioVendedor;

    @GetMapping
    public ResponseEntity<List<SellerDTO>> gettAllSellers() {
        List<SellerDTO> response = servicioVendedor.getAllSellers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SellerDTO> getSellerById(@PathVariable UUID id) {
        SellerDTO response = servicioVendedor.getSellerById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<SellerDTO>> getSellersByFilter(
            @RequestParam(required = false) Optional<String> nombre,
            @RequestParam(required = false) Optional<String> apellido,
            @RequestParam(required = false) Optional<String> email) {
        List<SellerDTO> response = servicioVendedor.getSellerByFilter(nombre, apellido, email);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<SellerDTO>> addSellers(@RequestBody List<SellerCreateDTO> vendedores) {
        List<SellerDTO> response = servicioVendedor.addSellers(vendedores);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> sellerDelete(@PathVariable UUID id) {
        servicioVendedor.sellerDelete(id);
        return ResponseEntity.noContent().build();
    }
}
