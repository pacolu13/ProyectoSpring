package com.proyecto.vendedor.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.excepciones.ResourceNotFoundException;
import com.proyecto.vendedor.dto.VendedorCreateDTO;
import com.proyecto.vendedor.dto.VendedorDTO;
import com.proyecto.vendedor.entity.Vendedor;
import com.proyecto.vendedor.mapper.VendedorMapper;
import com.proyecto.vendedor.repository.RepoVendedor;
import com.proyecto.vendedor.spec.BusquedaVendedor;

@Service
public class ServicioVendedor {

    private final RepoVendedor repoVendedor;
    private final VendedorMapper vendedorMapper;

    public ServicioVendedor(RepoVendedor repoVendedor, VendedorMapper vendedorMapper) {
        this.repoVendedor = repoVendedor;
        this.vendedorMapper = vendedorMapper;
    }

    public List<VendedorDTO> obtenerVendedores() {
        List<Vendedor> vendedores = repoVendedor.findAll();
        return vendedorMapper.toDTOList(vendedores);
    }

    public VendedorDTO obtenerVendedorPorId(Long id) {
        Vendedor vendedor = repoVendedor.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));
        return vendedorMapper.toDTO(vendedor);
    }

    public List<VendedorDTO> obtenerVendedorPorFiltro(String nombre, String apellido, String email) {
        BusquedaVendedor busqueda = new BusquedaVendedor(nombre, apellido, email);
        List<Vendedor> vendedores = repoVendedor.findAll(busqueda);
        if (vendedores == null || vendedores.isEmpty()) {
            throw new RuntimeException("No se encontraron vendedores con los filtros especificados");
        }
        return vendedorMapper.toDTOList(vendedores);
    }

    public List<VendedorDTO> añadirVendedores(List<VendedorCreateDTO> vendedores) {
        List<Vendedor> nuevo = vendedorMapper.toEntityList(vendedores);
        List<Vendedor> resultado = repoVendedor.saveAll(nuevo);
        return vendedorMapper.toDTOList(resultado);
    }

    public void eliminarCliente(Long id) {
        if(!repoVendedor.existsById(id)){
            throw new ResourceNotFoundException("No se pudo encontrar un vendedor para ese id");
        }
        repoVendedor.deleteById(id);
    }
}
