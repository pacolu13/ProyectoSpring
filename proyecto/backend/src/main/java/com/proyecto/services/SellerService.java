package com.proyecto.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.proyecto.DTOs.SellerDTO;
import com.proyecto.exceptions.ResourceNotFoundException;
import com.proyecto.mappers.SellerMapper;
import com.proyecto.models.User;
import com.proyecto.repositories.UserRepository;
import com.proyecto.spec.SellerSearch;

import lombok.RequiredArgsConstructor;

@Service
@SuppressWarnings("null")
@RequiredArgsConstructor

public class SellerService {

    private final UserRepository userRepository;
    private final SellerMapper sellerMapper;

    public List<SellerDTO> getAllSellers() {
        List<User> response = userRepository.findAll();
        return sellerMapper.toDTOList(response);
    }

    public SellerDTO getSellerById(UUID id) {
        User response = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));
        return sellerMapper.toDTO(response);
    }

    public List<SellerDTO> getSellerByFilter(Optional<String> nombre, Optional<String> apellido, Optional<String> email) {
        SellerSearch research = new SellerSearch(nombre, apellido, email);
        List<User> sellersList = userRepository.findAll(research);
        if (sellersList == null || sellersList.isEmpty()) {
            throw new RuntimeException("No se encontraron vendedores con los filtros especificados");
        }
        return sellerMapper.toDTOList(sellersList);
    }

    public void sellerDelete(UUID id) {
        if(!userRepository.existsById(id)){
            throw new ResourceNotFoundException("No se pudo encontrar un vendedor para ese id");
        }
        userRepository.deleteById(id);
    }
}
