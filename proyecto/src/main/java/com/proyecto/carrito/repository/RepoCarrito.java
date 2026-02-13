package com.proyecto.carrito.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.carrito.entity.Carrito;

public interface RepoCarrito extends JpaRepository<Carrito, Long> {

}
