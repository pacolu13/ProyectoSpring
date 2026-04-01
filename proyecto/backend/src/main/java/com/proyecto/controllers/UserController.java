package com.proyecto.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.user.dto.*;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.proyecto.DTOs.UserCreateDTO;
import com.proyecto.DTOs.UserDTO;
import com.proyecto.DTOs.UserUpdateDTO;
import com.proyecto.config.ApiRoutes;
import com.proyecto.services.UserService;


@RestController
@RequestMapping(ApiRoutes.USERS)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/this")
    public ResponseEntity<UserDTO> getUserByEmail(Authentication authentication) {
        String email = authentication.getName(); // extrae del JWT
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }
    
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> response = userService.getAllUsers();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID id) {
        UserDTO response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<List<UserDTO>> createUserList(@RequestBody List<UserCreateDTO> createUserDTOList) {
        List<UserDTO> response = userService.createUserList(createUserDTOList);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable UUID id, @RequestBody UserUpdateDTO updateUserDTO) {
        UserDTO response = userService.updateUser(id, updateUserDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
