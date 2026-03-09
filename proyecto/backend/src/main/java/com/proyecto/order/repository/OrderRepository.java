package com.proyecto.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
