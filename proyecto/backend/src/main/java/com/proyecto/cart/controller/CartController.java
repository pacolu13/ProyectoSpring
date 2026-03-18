package com.proyecto.cart.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.cart.dto.CartAddProductDTO;
import com.proyecto.cart.dto.CartDTO;
import com.proyecto.cart.service.CartService;
import com.proyecto.config.ApiRoutes;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(ApiRoutes.CARTS)
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<CartDTO> getCart(Authentication authentication) {
        String email = authentication.getName(); // extrae del JWT
        System.out.println("------------- EMAIL: " + email);
        return ResponseEntity.ok(cartService.getCartByEmail(email));
    }
    
    @GetMapping("/{clientId}")
    public ResponseEntity<CartDTO> findCart(@PathVariable UUID clientId) {
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
