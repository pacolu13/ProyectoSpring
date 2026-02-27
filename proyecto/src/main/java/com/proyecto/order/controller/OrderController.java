package com.proyecto.order.controller;

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
@RequestMapping("/compras")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/confirmar/{clienteId}")
    public ResponseEntity<OrderDTO> confirmarCompra(@PathVariable Long clienteId) {
        OrderDTO compraDTO = orderService.confirmarCompra(clienteId);
        return ResponseEntity.ok(compraDTO);
    }
}
