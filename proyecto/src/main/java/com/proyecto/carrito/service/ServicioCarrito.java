package com.proyecto.carrito.service;

import org.springframework.stereotype.Service;

import com.proyecto.carrito.dto.CarritoDTO;
import com.proyecto.carrito.entity.Carrito;
import com.proyecto.carrito.mapper.CarritoMapper;
import com.proyecto.carrito.repository.RepoCarrito;
import com.proyecto.excepciones.ResourceNotFoundException;

@Service
public class ServicioCarrito {

    private final RepoCarrito repoCarrito;
    private final CarritoMapper carritoMapper;

    public ServicioCarrito(RepoCarrito repoCarrito, CarritoMapper carritoMapper) {
        this.repoCarrito = repoCarrito;
        this.carritoMapper = carritoMapper;
    }

    public CarritoDTO obtenerCarrito(Long clienteId) {
        Carrito carrito = repoCarrito.findByClienteId(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Carrito no encontrado"));
        return carritoMapper.toDTO(carrito);
    }

}
