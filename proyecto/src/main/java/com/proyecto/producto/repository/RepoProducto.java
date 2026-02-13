package com.proyecto.producto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.producto.entity.Producto;

public interface RepoProducto extends JpaRepository<Producto, Long> {

}
