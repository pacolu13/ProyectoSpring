package com.proyecto.seller.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.proyecto.exceptions.ResourceNotFoundException;
import com.proyecto.seller.dto.SellerCreateDTO;
import com.proyecto.seller.dto.SellerDTO;
import com.proyecto.seller.mapper.SellerMapper;
import com.proyecto.seller.spec.SellerSearch;
import com.proyecto.user.entity.User;
import com.proyecto.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@SuppressWarnings("null")
@RequiredArgsConstructor

public class SellerService {

    private final UserRepository userRepository;
    private final SellerMapper sellerMapper;

    public List<SellerDTO> obtenerVendedores() {
        List<User> vendedores = userRepository.findAll();
        return sellerMapper.toDTOList(vendedores);
    }

    public SellerDTO obtenerVendedorPorId(UUID id) {
        User vendedor = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));
        return sellerMapper.toDTO(vendedor);
    }

    public List<SellerDTO> obtenerVendedorPorFiltro(Optional<String> nombre, Optional<String> apellido, Optional<String> email) {
        SellerSearch busqueda = new SellerSearch(nombre, apellido, email);
        List<User> vendedores = userRepository.findAll(busqueda);
        if (vendedores == null || vendedores.isEmpty()) {
            throw new RuntimeException("No se encontraron vendedores con los filtros especificados");
        }
        return sellerMapper.toDTOList(vendedores);
    }

    public List<SellerDTO> añadirVendedores(List<SellerCreateDTO> vendedores) {
        List<User> nuevo = sellerMapper.toEntityList(vendedores);
        List<User> resultado = userRepository.saveAll(nuevo);
        return sellerMapper.toDTOList(resultado);
    }

    public void eliminarCliente(UUID id) {
        if(!userRepository.existsById(id)){
            throw new ResourceNotFoundException("No se pudo encontrar un vendedor para ese id");
        }
        userRepository.deleteById(id);
    }
}
