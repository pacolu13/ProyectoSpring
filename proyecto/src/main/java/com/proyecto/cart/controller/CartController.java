package com.proyecto.cart.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.cart.dto.CartAddProductDTO;
import com.proyecto.cart.dto.CartDTO;
import com.proyecto.cart.service.CartService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<CartDTO> findCart(@PathVariable Long clientId) {
        CartDTO response = cartService.findCart(clientId);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CartDTO> addProductToCart(@RequestBody CartAddProductDTO dto) {

        System.out.println(dto);
        CartDTO response = cartService.addProduct(dto);
        return ResponseEntity.ok(response);
    }

}
