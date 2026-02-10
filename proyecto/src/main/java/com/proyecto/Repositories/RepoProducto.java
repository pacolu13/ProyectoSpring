package com.proyecto.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.Entities.Producto;

public interface RepoProducto extends JpaRepository<Producto, Long> {

}
