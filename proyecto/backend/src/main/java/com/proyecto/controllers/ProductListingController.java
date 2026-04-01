package com.proyecto.controllers;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.DTOs.ProductListingCreateDTO;
import com.proyecto.DTOs.ProductListingDTO;
import com.proyecto.config.ApiRoutes;
import com.proyecto.services.ProductListingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiRoutes.PRODUCTS_LISTING)
@RequiredArgsConstructor

public class ProductListingController {

    private final ProductListingService productListingService;

    @GetMapping
    public ResponseEntity<List<ProductListingDTO>> getAll() {
        List<ProductListingDTO> response = productListingService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<List<ProductListingDTO>> getAllProductListing(@PathVariable Long idProduct) {
        List<ProductListingDTO> response = productListingService.findAllProductsListing(idProduct);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/seller")
    public ResponseEntity<List<ProductListingDTO>> getAllSellerListings(Authentication authentication) {
        String email = authentication.getName();
        List<ProductListingDTO> response = productListingService.findAllSellerListings(email);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ProductListingDTO> addProductListing(Authentication authentication,
            @RequestBody ProductListingCreateDTO productListing) {
        String email = authentication.getName();
        ProductListingDTO response = productListingService.addProductListing(productListing, email);
        return ResponseEntity.ok(response);
    }
}
