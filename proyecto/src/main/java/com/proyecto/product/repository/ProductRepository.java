package com.proyecto.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
