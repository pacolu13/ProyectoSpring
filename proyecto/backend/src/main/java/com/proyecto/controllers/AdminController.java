package com.proyecto.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.config.ApiRoutes;
import com.proyecto.services.AdminService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(ApiRoutes.ADMIN)
public class AdminController {

    private final AdminService adminService;
    
    //Eliminar una publicación
    @DeleteMapping("/listings/{listingId}")
    public void deleteProductListing(Authentication authentication, @PathVariable Long listingId) {
        String email = authentication.getName();
        adminService.deletePost(listingId, email);

    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(Authentication authentication, @PathVariable Long userId) {
        String email = authentication.getName();
        // Lógica para eliminar el usuario
    }
    

}
