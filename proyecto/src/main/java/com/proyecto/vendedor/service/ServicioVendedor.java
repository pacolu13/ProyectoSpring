package com.proyecto.vendedor.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.vendedor.dto.VendedorDTO;
import com.proyecto.vendedor.entity.Vendedor;
import com.proyecto.vendedor.repository.RepoVendedor;
import com.proyecto.vendedor.spec.BusquedaVendedor;

@Service
public class ServicioVendedor {

    private final RepoVendedor repoVendedor;

    public ServicioVendedor(RepoVendedor repoVendedor) {
        this.repoVendedor = repoVendedor;
    }

    public List<VendedorDTO> obtenerVendedores() {
        List<Vendedor> vendedores = repoVendedor.findAll();
        return vendedores.stream().map(v -> {
            return new VendedorDTO(v.getId(), v.getNombre(), v.getApellido(), v.getEmail());
        }).toList();
    }

    public VendedorDTO obtenerVendedorPorId(Long id){
        Vendedor vendedor = repoVendedor.findById(id).orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));
        return new VendedorDTO(vendedor.getId(),vendedor.getNombre(), vendedor.getApellido(), vendedor.getEmail());
    }

    public VendedorDTO obtenerVendedorPorFiltro(String nombre, String apellido, String email){
        BusquedaVendedor busqueda = new BusquedaVendedor(nombre, apellido, email);
        List<Vendedor> vendedores = repoVendedor.findAll(busqueda);
        if (vendedores == null || vendedores.isEmpty()) {
            throw new RuntimeException("No se encontraron vendedores con los filtros especificados");
        }
        Vendedor vendedor = vendedores.get(0);
        return new VendedorDTO(vendedor.getId(), vendedor.getNombre(), vendedor.getApellido(), vendedor.getEmail());
    }
}
