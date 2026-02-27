package com.proyecto.seller.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.exceptions.ResourceNotFoundException;
import com.proyecto.seller.dto.SellerCreateDTO;
import com.proyecto.seller.dto.SellerDTO;
import com.proyecto.seller.entity.Seller;
import com.proyecto.seller.mapper.SellerMapper;
import com.proyecto.seller.repository.SellerRepository;
import com.proyecto.seller.spec.SellerSearch;

@Service
@SuppressWarnings("null")
public class SellerService {

    private final SellerRepository repoVendedor;
    private final SellerMapper vendedorMapper;

    public SellerService(SellerRepository repoVendedor, SellerMapper vendedorMapper) {
        this.repoVendedor = repoVendedor;
        this.vendedorMapper = vendedorMapper;
    }

    public List<SellerDTO> obtenerVendedores() {
        List<Seller> vendedores = repoVendedor.findAll();
        return vendedorMapper.toDTOList(vendedores);
    }

    public SellerDTO obtenerVendedorPorId(Long id) {
        Seller vendedor = repoVendedor.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));
        return vendedorMapper.toDTO(vendedor);
    }

    public List<SellerDTO> obtenerVendedorPorFiltro(String nombre, String apellido, String email) {
        SellerSearch busqueda = new SellerSearch(nombre, apellido, email);
        List<Seller> vendedores = repoVendedor.findAll(busqueda);
        if (vendedores == null || vendedores.isEmpty()) {
            throw new RuntimeException("No se encontraron vendedores con los filtros especificados");
        }
        return vendedorMapper.toDTOList(vendedores);
    }

    public List<SellerDTO> añadirVendedores(List<SellerCreateDTO> vendedores) {
        List<Seller> nuevo = vendedorMapper.toEntityList(vendedores);
        List<Seller> resultado = repoVendedor.saveAll(nuevo);
        return vendedorMapper.toDTOList(resultado);
    }

    public void eliminarCliente(Long id) {
        if(!repoVendedor.existsById(id)){
            throw new ResourceNotFoundException("No se pudo encontrar un vendedor para ese id");
        }
        repoVendedor.deleteById(id);
    }
}
