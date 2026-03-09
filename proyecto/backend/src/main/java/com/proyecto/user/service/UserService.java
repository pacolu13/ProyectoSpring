package com.proyecto.user.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.proyecto.cart.entity.Cart;
import com.proyecto.user.dto.UserCreateDTO;
import com.proyecto.user.dto.UserDTO;
import com.proyecto.user.dto.UserUpdateDTO;
import com.proyecto.user.entity.User;
import com.proyecto.user.mapper.UserMapper;
import com.proyecto.user.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDTO> getAllUsers() {
        return userMapper.toDTOList(userRepository.findAll());
    }

    @SuppressWarnings("null")
    public UserDTO getUserById(UUID id) {
        return userMapper.toDTO(userRepository.findById(id).orElse(null));

    }

    public UserDTO createUser(UserCreateDTO createUserDTO) {
        User user = userMapper.toEntity(createUserDTO);
        @SuppressWarnings("null")
        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }

    public List<UserDTO> createUserList(List<UserCreateDTO> createUserDTOList) {
        List<User> users = userMapper.toEntityList(createUserDTOList);
        for(User user : users){
            Cart cart = new Cart();
            cart.setUser(user);
            user.setCart(cart);
        }
        @SuppressWarnings("null")
        List<User> savedUsers = userRepository.saveAll(users);
        return userMapper.toDTOList(savedUsers);
    }

    public UserDTO updateUser(UUID id, UserUpdateDTO updateUserDTO) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @SuppressWarnings("null")
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

}
