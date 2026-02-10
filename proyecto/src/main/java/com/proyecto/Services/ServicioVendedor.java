package com.proyecto.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.DTOs.VendedorDTO;
import com.proyecto.Entities.Vendedor;
import com.proyecto.Repositories.RepoVendedor;

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
