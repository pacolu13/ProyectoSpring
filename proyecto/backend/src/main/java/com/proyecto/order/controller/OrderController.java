package com.proyecto.order.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.config.ApiRoutes;
import com.proyecto.order.dto.OrderDTO;
import com.proyecto.order.service.OrderService;

import lombok.AllArgsConstructor;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(ApiRoutes.ORDERS)
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/submit/{clientId}")
    public ResponseEntity<OrderDTO> orderSubmit(@PathVariable UUID clientId) {
        OrderDTO compraDTO = orderService.orderSubmit(clientId);
        return ResponseEntity.ok(compraDTO);
    }
}
