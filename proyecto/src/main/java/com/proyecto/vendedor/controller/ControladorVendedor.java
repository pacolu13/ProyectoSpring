package com.proyecto.vendedor.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.vendedor.dto.VendedorCreateDTO;
import com.proyecto.vendedor.dto.VendedorDTO;
import com.proyecto.vendedor.service.ServicioVendedor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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

    @PostMapping
    public ResponseEntity<List<VendedorDTO>> añadirListaVendedores(@RequestBody List<VendedorCreateDTO> vendedores){
        List<VendedorDTO> response = servicioVendedor.añadirVendedores(vendedores);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVendedor(@PathVariable Long id) {
        servicioVendedor.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
