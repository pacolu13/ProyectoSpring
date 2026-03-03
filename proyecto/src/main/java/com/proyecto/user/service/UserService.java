package com.proyecto.user.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

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

    public UserDTO getUserById(UUID id) {
        return userMapper.toDTO(userRepository.findById(id).orElse(null));
               
    }

    public UserDTO createUser(UserCreateDTO createUserDTO) {
        User user = userMapper.toEntity(createUserDTO);
        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }

    public UserDTO updateUser(UUID id, UserUpdateDTO updateUserDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    public void deleteUser(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

}
