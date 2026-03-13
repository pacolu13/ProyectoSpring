package com.proyecto.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.user.dto.*;
import com.proyecto.user.service.UserService;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.proyecto.config.ApiRoutes;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(ApiRoutes.USERS)
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    /*
    Pasar todos los metodos a EntityResponse
    */
   
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserCreateDTO createUserDTO) {
        return userService.createUser(createUserDTO);
    }

    @PostMapping("/list")
    public List<UserDTO> createUserList(@RequestBody List<UserCreateDTO> createUserDTOList) {
        return userService.createUserList(createUserDTOList);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable UUID id, @RequestBody UserUpdateDTO updateUserDTO) {
        return userService.updateUser(id, updateUserDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }

}
