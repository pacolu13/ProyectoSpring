package com.proyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.models.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {

}
