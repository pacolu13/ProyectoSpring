package com.proyecto.vendedor.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.vendedor.dto.VendedorDTO;
import com.proyecto.vendedor.entity.Vendedor;
import com.proyecto.vendedor.repository.RepoVendedor;

@Service
public class ServicioVendedor {

    private final RepoVendedor repoVendedor;

    public ServicioVendedor(RepoVendedor repoVendedor) {
        this.repoVendedor = repoVendedor;
    }

    public List<VendedorDTO> obtenerVendedores() {
        List<Vendedor> vendedores = repoVendedor.findAll();
        return vendedores.stream().map(v -> {
            return new VendedorDTO(v.getNombre(), v.getApellido(), v.getEmail());
        }).toList();
    }
}
