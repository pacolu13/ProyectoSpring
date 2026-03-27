package com.proyecto.productListing.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.config.ApiRoutes;
import com.proyecto.productListing.dto.ProductListingCreateDTO;
import com.proyecto.productListing.dto.ProductListingDTO;
import com.proyecto.productListing.service.ProductListingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiRoutes.PRODUCTS_LISTING)
@RequiredArgsConstructor

public class ProductListingController {

    private final ProductListingService productListingService;

    @GetMapping("/{idProduct}")
    public ResponseEntity<List<ProductListingDTO>> getAllProductListing(@PathVariable Long idProduct) {
        List<ProductListingDTO> response = productListingService.findAllProductsListing(idProduct);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<List<ProductListingDTO>> añadirListaProductosVenta(Authentication authentication,
            @RequestBody List<ProductListingCreateDTO> listaProductosVenta) {
        String email = authentication.getName();
        List<ProductListingDTO> response = productListingService.addProductListingList(listaProductosVenta, email);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
