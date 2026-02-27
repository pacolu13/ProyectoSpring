package com.proyecto.cart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.cart.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long>{
    
    Optional<Cart> findByClientId(Long clientId);
}

