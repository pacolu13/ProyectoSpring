package com.proyecto.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.DTOs.CartAddProductDTO;
import com.proyecto.DTOs.CartDTO;
import com.proyecto.DTOs.CartUpdateDTO;
import com.proyecto.config.ApiRoutes;
import com.proyecto.services.CartService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(ApiRoutes.CARTS)
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<CartDTO> getCart(Authentication authentication) {
        String email = authentication.getName(); // extrae del JWT
        return ResponseEntity.ok(cartService.getCartByEmail(email));
    }

    @PutMapping
    public ResponseEntity<CartDTO> updateQuantity(Authentication authentication, @RequestBody CartUpdateDTO dto) {
        String email = authentication.getName();
        CartDTO response = cartService.updateCart(email, dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CartDTO> addProductToCart(Authentication authentication, @RequestBody CartAddProductDTO dto) {
        String email = authentication.getName();
        CartDTO response = cartService.addProduct(email, dto);
        return ResponseEntity.ok(response);
    }

}
