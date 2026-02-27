package com.proyecto.order.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.order.dto.OrderDTO;
import com.proyecto.order.service.OrderService;

import org.springframework.web.bind.annotation.PostMapping;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/confirmar/{clientId}")
    public ResponseEntity<OrderDTO> confirmarCompra(@PathVariable UUID clientId) {
        OrderDTO compraDTO = orderService.confirmarCompra(clientId);
        return ResponseEntity.ok(compraDTO);
    }
}
