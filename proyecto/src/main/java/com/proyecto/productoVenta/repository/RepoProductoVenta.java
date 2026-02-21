package com.proyecto.productoVenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.productoVenta.entity.ProductoVenta;

@Repository
public interface RepoProductoVenta extends JpaRepository<ProductoVenta, Long> {

    
}