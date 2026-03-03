package com.proyecto.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.user.dto.UserCreateDTO;
import com.proyecto.user.dto.UserDTO;
import com.proyecto.user.dto.UserUpdateDTO;
import com.proyecto.user.service.UserService;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/users")
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
    public UserDTO getUserById(@RequestParam Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserCreateDTO createUserDTO) {
        return userService.createUser(createUserDTO);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserUpdateDTO updateUserDTO) {
        return userService.updateUser(id, updateUserDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
    }

}
