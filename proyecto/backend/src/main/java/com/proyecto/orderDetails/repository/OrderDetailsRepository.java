package com.proyecto.orderDetails.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.orderDetails.entity.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {

}
