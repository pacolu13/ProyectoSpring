package com.proyecto.vendedor.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.vendedor.dto.VendedorDTO;
import com.proyecto.vendedor.service.ServicioVendedor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/vendedores")
public class ControladorVendedor {

    private final ServicioVendedor servicioVendedor;

    public ControladorVendedor(ServicioVendedor servicioVendedor) {
        this.servicioVendedor = servicioVendedor;
    }

    @GetMapping
    public List<VendedorDTO> obtenerVendedores() {
        return servicioVendedor.obtenerVendedores();
    }

    @GetMapping("/{id}")
    public VendedorDTO obtenerVendedorPorId(@PathVariable Long id) {
        return servicioVendedor.obtenerVendedorPorId(id);
    }

    @GetMapping("/filtrar")
    public List<VendedorDTO> obtenerVendedoresPorFiltro(
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String email) {
        return servicioVendedor.obtenerVendedorPorFiltro(nombre, apellido, email);
    }

}
