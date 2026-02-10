package com.proyecto.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.Entities.Carrito;

public interface RepoCarrito extends JpaRepository<Carrito, Long> {

}
