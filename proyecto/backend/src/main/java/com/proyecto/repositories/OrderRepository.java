package com.proyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
