package com.proyecto.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.DTOs.OrderDTO;
import com.proyecto.config.ApiRoutes;
import com.proyecto.services.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiRoutes.ORDERS)
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/submit")
    public ResponseEntity<OrderDTO> orderSubmit(Authentication authentication) {
        String email = authentication.getName();
        OrderDTO compraDTO = orderService.orderSubmit(email);
        return ResponseEntity.ok(compraDTO);
    }
}
