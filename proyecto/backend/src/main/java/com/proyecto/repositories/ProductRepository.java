package com.proyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
