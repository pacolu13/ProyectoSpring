package com.proyecto.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.proyecto.DTOs.UserCreateDTO;
import com.proyecto.DTOs.UserDTO;
import com.proyecto.config.ExceptionFactory;
import com.proyecto.mappers.UserMapper;
import com.proyecto.models.Cart;
import com.proyecto.models.User;
import com.proyecto.models.UserUpdateDTO;
import com.proyecto.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@SuppressWarnings("null")
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDTO> getAllUsers() {
        return userMapper.toDTOList(userRepository.findAll());
    }

    public UserDTO getUserByEmail(String email) {
        User response = userRepository.findByEmail(email)
                .orElseThrow(() -> ExceptionFactory.createUserNotFoundException());
        return userMapper.toDTO(response);

    }

    public UserDTO getUserById(UUID id) {
        return userMapper.toDTO(userRepository.findById(id).orElse(null));

    }

    public List<UserDTO> createUserList(List<UserCreateDTO> createUserDTOList) {
        List<User> users = userMapper.toEntityList(createUserDTOList);
        for (User user : users) {
            Cart cart = new Cart();
            cart.setUser(user);
            user.setCart(cart);
        }
        List<User> savedUsers = userRepository.saveAll(users);
        return userMapper.toDTOList(savedUsers);
    }

    public UserDTO updateUser(UUID id, UserUpdateDTO updateUserDTO) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

}
