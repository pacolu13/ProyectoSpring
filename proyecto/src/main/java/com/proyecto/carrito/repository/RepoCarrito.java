package com.proyecto.carrito.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.carrito.entity.Carrito;

@Repository
public interface RepoCarrito extends JpaRepository<Carrito,Long>{
    
    Optional<Carrito> findByClienteId(Long clienteId);
}

